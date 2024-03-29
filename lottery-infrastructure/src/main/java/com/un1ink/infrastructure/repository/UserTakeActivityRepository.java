package com.un1ink.infrastructure.repository;

import com.alibaba.fastjson.JSON;
import com.un1ink.common.constants.TakeState;
import com.un1ink.domain.activity.model.vo.*;
import com.un1ink.domain.activity.repository.IUserTakeActivityRepository;
import com.un1ink.infrastructure.dao.IActivityDao;
import com.un1ink.infrastructure.dao.IUserStrategyExportDao;
import com.un1ink.infrastructure.dao.IUserTakeActivityCountDao;
import com.un1ink.infrastructure.dao.IUserTakeActivityDao;
import com.un1ink.infrastructure.po.Activity;
import com.un1ink.infrastructure.po.UserStrategyExport;
import com.un1ink.infrastructure.po.UserTakeActivity;
import com.un1ink.infrastructure.po.UserTakeActivityCount;
import org.apache.catalina.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @description: 用户抽奖状态信息仓储服务
 * @author：un1ink
 * @date: 2023/3/29
 */
@Repository
public class UserTakeActivityRepository implements IUserTakeActivityRepository {

    @Resource
    private IUserTakeActivityCountDao userTakeActivityCountDao;

    @Resource
    private IUserTakeActivityDao userTakeActivityDao;

    @Resource
    private IUserStrategyExportDao userStrategyExportDao;

    @Resource
    private IActivityDao activityDao;

    @Override
    public int subtractionLeftCount(Long activityId, String activityName, Integer takeCount, Integer userTakeLeftCount, String uId, Date partakeDate) {
        if(null == userTakeLeftCount) {
            UserTakeActivityCount userTakeActivityCount = new UserTakeActivityCount();
            userTakeActivityCount.setUId(uId);
            userTakeActivityCount.setActivityId(activityId);
            userTakeActivityCount.setTotalCount(takeCount);
            userTakeActivityCount.setLeftCount(takeCount - 1);
            userTakeActivityCountDao.insert(userTakeActivityCount);
            return 1;
        } else {
            UserTakeActivityCount userTakeActivityCount = new UserTakeActivityCount();
            userTakeActivityCount.setUId(uId);
            userTakeActivityCount.setActivityId(activityId);
            return userTakeActivityCountDao.updateLeftCount(userTakeActivityCount);
        }
    }

    @Override
    public void takeActivity(Long activityId, String activityName, Integer takeCount, Integer userTakeLeftCount, String uId, Date partakeDate, Long takeId) {
        UserTakeActivity userTakeActivity = new UserTakeActivity();
        userTakeActivity.setUId(uId);
        userTakeActivity.setTakeId(takeId);
        userTakeActivity.setActivityId(activityId);
        userTakeActivity.setActivityName(activityName);
        userTakeActivity.setTakeDate(partakeDate);
        if (null == userTakeLeftCount) {
            // 第一次参加
            userTakeActivity.setTakeCount(1);
        } else {
            // 非第一次参加
            userTakeActivity.setTakeCount(takeCount - userTakeLeftCount);
        }
        userTakeActivity.setState(TakeState.NO_USED.getCode());
        String uuid = uId + "_" + activityId + "_" + userTakeActivity.getTakeCount();
        userTakeActivity.setUuid(uuid);
        userTakeActivityDao.insert(userTakeActivity);
    }

    @Override
    public int lockTakeActivity(String uId, Long activityId, Long takeId) {
        UserTakeActivity userTakeActivity = new UserTakeActivity();
        userTakeActivity.setUId(uId);
        userTakeActivity.setActivityId(activityId);
        userTakeActivity.setTakeId(takeId);
        return userTakeActivityDao.lockTackActivity(userTakeActivity);
    }

    @Override
    public void saveUserStrategyExport(DrawOrderVO drawOrder) {
        UserStrategyExport userStrategyExport = new UserStrategyExport();
        userStrategyExport.setUId(drawOrder.getUId());
        userStrategyExport.setActivityId(drawOrder.getActivityId());
        userStrategyExport.setOrderId(drawOrder.getOrderId());
        userStrategyExport.setStrategyId(drawOrder.getStrategyId());
        userStrategyExport.setStrategyMode(drawOrder.getStrategyMode());
        userStrategyExport.setGrantType(drawOrder.getGrantType());
        userStrategyExport.setGrantDate(drawOrder.getGrantDate());
        userStrategyExport.setGrantState(drawOrder.getGrantState());
        userStrategyExport.setAwardId(drawOrder.getAwardId());
        userStrategyExport.setAwardType(drawOrder.getAwardType());
        userStrategyExport.setAwardName(drawOrder.getAwardName());
        userStrategyExport.setAwardContent(drawOrder.getAwardContent());
        userStrategyExport.setUuid(String.valueOf(drawOrder.getOrderId()));
        userStrategyExportDao.insert(userStrategyExport);

    }

    @Override
    public UserTakeActivityVO queryNoConsumedTakeActivityOrder(Long activityId, String uId) {
        UserTakeActivity userTakeActivity = new UserTakeActivity();
        userTakeActivity.setUId(uId);
        userTakeActivity.setActivityId(activityId);
        UserTakeActivity noConsumedTakeActivityOrder = userTakeActivityDao.queryNoConsumedTakeActivityOrder(userTakeActivity);
        // 不存在未使用活动卷对象
        if(null == noConsumedTakeActivityOrder) {
            return null;
        }

        UserTakeActivityVO userTakeActivityVO = new UserTakeActivityVO();
        userTakeActivityVO.setStrategyId(noConsumedTakeActivityOrder.getActivityId());
        userTakeActivityVO.setActivityId(noConsumedTakeActivityOrder.getActivityId());
        userTakeActivityVO.setTakeId(noConsumedTakeActivityOrder.getTakeId());
        userTakeActivityVO.setState(noConsumedTakeActivityOrder.getState());

        return userTakeActivityVO;
    }

    @Override
    public InvoiceVO getInvoiceByActivityMQState(String uId, Long orderId) {
        UserStrategyExport userStrategyExport = new UserStrategyExport();
        userStrategyExport.setUId(uId);
        userStrategyExport.setOrderId(orderId);
        UserStrategyExport lossUserStrategyExport =  userStrategyExportDao.getUserStrategyExportByActivityMQState(userStrategyExport);
//        logger.info("invoiceVO:{}"+ JSON.toJSONString(invoiceVO));
        InvoiceVO invoiceVO = new InvoiceVO();
        invoiceVO.setUId(lossUserStrategyExport.getUId());
        invoiceVO.setOrderId(lossUserStrategyExport.getOrderId());
        invoiceVO.setAwardId(lossUserStrategyExport.getAwardId());
        invoiceVO.setAwardType(lossUserStrategyExport.getAwardType());
        invoiceVO.setAwardName(lossUserStrategyExport.getAwardName());
        invoiceVO.setAwardContent(lossUserStrategyExport.getAwardContent());
        return invoiceVO;
    }

    @Override
    public void updateActivityStock(ActivityPartakeRecordVO activityPartakeRecordVO) {
        Activity activity = new Activity();
        activity.setActivityId(activityPartakeRecordVO.getActivityId());
        activity.setStockSurplusCount(activityPartakeRecordVO.getStockSurplusCount());
        activityDao.updateActivityStock(activity);

    }


}
