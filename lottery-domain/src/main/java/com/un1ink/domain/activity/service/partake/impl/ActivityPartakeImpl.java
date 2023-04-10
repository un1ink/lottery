package com.un1ink.domain.activity.service.partake.impl;

import com.alibaba.fastjson.JSON;
import com.un1ink.common.Result;
import com.un1ink.common.constants.ActivityState;
import com.un1ink.common.constants.IdGeneratorMethod;
import com.un1ink.common.constants.MQState;
import com.un1ink.domain.activity.model.req.PartakeReq;
import com.un1ink.domain.activity.model.vo.*;
import com.un1ink.domain.activity.repository.IActivityMQStateRepository;
import com.un1ink.domain.activity.repository.IUserTakeActivityRepository;
import com.un1ink.domain.activity.service.partake.BaseActivityPartake;
import com.un1ink.domain.activity.service.partake.IActivityPartake;
import com.un1ink.domain.support.ids.IIdGenerator;
import com.un1ink.common.constants.ResponseCode;
import com.un1ink.middleware.db.router.strategy.IDBRouterStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @description: 活动参与功能实现
 * @author：un1ink
 * @date: 2023/3/29
 */
@Service
public class ActivityPartakeImpl extends BaseActivityPartake {

    private final Logger logger = LoggerFactory.getLogger(ActivityPartakeImpl.class);

    @Resource
    private IUserTakeActivityRepository userTakeActivityRepository;

    @Resource
    private IActivityMQStateRepository activityMQStateRepository;

    @Resource
    private Map<IdGeneratorMethod, IIdGenerator> idGeneratorMap;

    @Resource
    private TransactionTemplate transactionTemplate;

    @Resource
    private IDBRouterStrategy dbRouter;

    @Override
    protected UserTakeActivityVO queryNoConsumedTakeActivityOrder(Long activityId, String uId) {
        return userTakeActivityRepository.queryNoConsumedTakeActivityOrder(activityId, uId);
    }

    @Override
    protected Result checkActivity(PartakeReq req, ActivityBillVO bill) {
        if (!ActivityState.DOING.getCode().equals(bill.getState())) {
            logger.warn("活动当前状态非可用 state：{}", bill.getState());
            return Result.buildResult(ResponseCode.UN_ERROR, "活动当前状态非可用");
        }

        // 校验：活动日期
        if (bill.getBeginDateTime().after(req.getPartakeDate()) || bill.getEndDateTime().before(req.getPartakeDate())) {
            logger.warn("活动时间范围非可用 beginDateTime：{} endDateTime：{}", bill.getBeginDateTime(), bill.getEndDateTime());
            return Result.buildResult(ResponseCode.UN_ERROR, "活动时间范围非可用");
        }

        // 校验：活动库存
        if (bill.getStockSurplusCount() <= 0) {
            logger.warn("活动剩余库存非可用 stockSurplusCount：{}", bill.getStockSurplusCount());
            return Result.buildResult(ResponseCode.UN_ERROR, "活动剩余库存非可用");
        }

        // 校验：个人库存 - 个人活动剩余可参与次数
        if(bill.getUserTakeLeftCount() == null){
            // 该用户未参加过该活动
            return Result.buildSuccessResult();
        }
        if (bill.getUserTakeLeftCount() <= 0) {
            logger.warn("个人参与次数非可用 userTakeLeftCount：{}", bill.getUserTakeLeftCount());
            return Result.buildResult(ResponseCode.UN_ERROR, "个人领取次数非可用");
        }

        return Result.buildSuccessResult();
    }

    @Override
    protected Result subtractionActivityStock(PartakeReq req) {
        int count = activityRepository.subtractionActivityStock(req.getActivityId());
        if(0 == count) {
            logger.error("扣减活动库存失败 activityId：{}", req.getActivityId());
            return Result.buildResult(ResponseCode.NO_UPDATE);
        }
        return Result.buildSuccessResult();
    }

    @Override
    protected Result grabActivity(PartakeReq req, ActivityBillVO bill, Long takeId) {
        try{
            dbRouter.doRouter(req.getUId());
            return transactionTemplate.execute(status -> {
               try {
                   // 扣减个人已参与次数
                   int updateCont = userTakeActivityRepository.subtractionLeftCount(bill.getActivityId(), bill.getActivityName(), bill.getTakeCount(), bill.getUserTakeLeftCount(), bill.getUId(), req.getPartakeDate());
                   if (0 == updateCont) {
                       status.setRollbackOnly();
                       logger.info("参与活动，扣减个人参与次数失败 activityId:{} uId:{}", bill.getActivityId(), bill.getUId());
                       return Result.buildResult(ResponseCode.NO_UPDATE);
                   }
                   // 插入领取活动信息
                   userTakeActivityRepository.takeActivity(bill.getActivityId(), bill.getActivityName(), bill.getTakeCount(), bill.getUserTakeLeftCount(), bill.getUId(), req.getPartakeDate(), takeId);
               } catch (Exception e) {
                   // 如果触发一般是takeActivity异常
                   status.setRollbackOnly();
                   logger.error("领取活动，唯一索引冲突 activityId：{} uId：{}", req.getActivityId(), req.getUId(), e);
                   return Result.buildResult(ResponseCode.INDEX_DUP);
               }
               return Result.buildSuccessResult();
            });
        } finally {
            dbRouter.clear();
        }
    }

    @Override
    public Result recordDrawOrder(DrawOrderVO drawOrder) {
        try {
            dbRouter.doRouter(drawOrder.getUId());
            return transactionTemplate.execute(status -> {
                try {
                    int lockCount = userTakeActivityRepository.lockTakeActivity(drawOrder.getUId(), drawOrder.getActivityId(), drawOrder.getTakeId());
                    if(lockCount == 0) {
                        status.setRollbackOnly();
                        logger.error("记录中奖单，个人参与活动抽奖已消耗完 activityId：{} uId：{}", drawOrder.getActivityId(), drawOrder.getUId());
                        return Result.buildResult(ResponseCode.INDEX_DUP);
                    }

                    // 保存抽奖信息
                    userTakeActivityRepository.saveUserStrategyExport(drawOrder);
                    // 本地消息表mq状态
                    activityMQStateRepository.insertInvoiceMqState(drawOrder.getUId(), drawOrder.getOrderId(), MQState.INIT.getCode());

                } catch (DuplicateKeyException e) {
                    status.setRollbackOnly();
                    logger.error("记录中奖单，唯一索引冲突 activityId:{}, uId:{}", drawOrder.getActivityId(), drawOrder.getUId());
                    return Result.buildResult(ResponseCode.INDEX_DUP);
                }
                return Result.buildSuccessResult();
            });
        } finally {
            dbRouter.clear();
        }
    }

    @Override
    public List<InvoiceVO> scanInvoiceMqState(int dbCount) {

        try {
            dbRouter.setDBKey(dbCount);
            dbRouter.setTBKey(0);
            List<InvoiceVO> InvoiceVOList = new ArrayList<>();
            List<ActivityMQStateVO> activityMQStateVOList = activityMQStateRepository.scanInvoiceMqState();
            if (0 == activityMQStateVOList.size()) {
                logger.info("数据库 {} 无消息丢失，扫描完成", dbCount);
                return InvoiceVOList;
            }

            for (ActivityMQStateVO activityMQStateVO : activityMQStateVOList) {
                InvoiceVO invoiceVO = userTakeActivityRepository.getInvoiceByActivityMQState(activityMQStateVO.getUId(), activityMQStateVO.getOrderId());
                logger.info("invoiceVO:{}", JSON.toJSONString(invoiceVO));
                InvoiceVOList.add(invoiceVO);
            }
            return InvoiceVOList;

        } catch (Exception e){
            logger.info("获取本地消息表中丢失消息失败！");
            e.printStackTrace();
            return new ArrayList<>();
        }
        finally {
            dbRouter.clear();
        }
    }


}
