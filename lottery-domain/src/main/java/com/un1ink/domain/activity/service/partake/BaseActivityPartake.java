package com.un1ink.domain.activity.service.partake;

import com.alibaba.fastjson.JSON;
import com.un1ink.common.Result;
import com.un1ink.common.constants.IdGeneratorMethod;
import com.un1ink.domain.activity.model.req.PartakeReq;
import com.un1ink.domain.activity.model.res.PartakeRes;
import com.un1ink.domain.activity.model.res.StockRes;
import com.un1ink.domain.activity.model.vo.ActivityBillVO;
import com.un1ink.common.constants.ResponseCode;
import com.un1ink.domain.activity.model.vo.UserTakeActivityVO;
import com.un1ink.domain.support.ids.IIdGenerator;

import javax.annotation.Resource;
import java.text.AttributedCharacterIterator;
import java.util.Map;

/**
 * @description:
 * @author：un1ink
 * @date: 2023/3/29
 */
public abstract class BaseActivityPartake extends ActivityPartakeSupport implements IActivityPartake{
    @Resource
    private Map<IdGeneratorMethod, IIdGenerator> idGeneratorMap;

    @Override
    public PartakeRes doPartake(PartakeReq req) {
        // 1. 查询是否存在未执行抽奖领取活动单【user_take_activity 存在 state = 0，领取了但抽奖过程失败的，可以直接返回领取结果继续抽奖】
        UserTakeActivityVO userTakeActivityVO = this.queryNoConsumedTakeActivityOrder(req.getActivityId(), req.getUId());
        if (null != userTakeActivityVO) {
            return buildPartakeResult(userTakeActivityVO.getStrategyId(), userTakeActivityVO.getTakeId());
        }

        // 2. 查询活动账单
        ActivityBillVO activityBillVO = super.queryActivityBill(req);


        // 3. 活动信息校验处理【活动库存、状态、日期、个人参与次数】
        Result checkResult = this.checkActivity(req, activityBillVO);
        if(!ResponseCode.SUCCESS.getCode().equals(checkResult.getCode())) {
            return new PartakeRes(checkResult.getCode(), checkResult.getInfo());
        }

        // 4. 扣减活动库存，通过Redis【活动库存扣减编号，作为锁的Key，缩小颗粒度】 Begin
        StockRes subtractionActivityResult = this.subtractionActivityStockByRedis(req.getUId(), req.getActivityId(), activityBillVO.getStockCount());

        if (!ResponseCode.SUCCESS.getCode().equals(subtractionActivityResult.getCode())) {
            this.recoverActivityCacheStockByRedis(req.getActivityId(), subtractionActivityResult.getStockKey(), subtractionActivityResult.getCode());
            return new PartakeRes(subtractionActivityResult.getCode(), subtractionActivityResult.getInfo());
        }

        // 5. 插入领取活动信息【个人用户把活动信息写入到用户表】
        Long takeId = idGeneratorMap.get(IdGeneratorMethod.SnowFlake).nextId();
        Result grabResult = this.grabActivity(req, activityBillVO, takeId);
        if(!ResponseCode.SUCCESS.getCode().equals(grabResult.getCode())) {
            this.recoverActivityCacheStockByRedis(req.getActivityId(), subtractionActivityResult.getStockKey(), subtractionActivityResult.getCode());
            return new PartakeRes(grabResult.getCode(), grabResult.getInfo());
        }

        // 6. 封装结果【返回的策略ID，用于继续完成抽奖步骤】
        PartakeRes partakeRes = new PartakeRes(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getInfo());
        partakeRes.setStrategyId(activityBillVO.getStrategyId());
        return partakeRes;
    }

    /**
     * 查询是否存在未执行抽奖领取活动单【user_take_activity 存在 state = 0，领取了但抽奖过程失败的，可以直接返回领取结果继续抽奖】
     *
     * @param activityId 活动ID
     * @param uId        用户ID
     * @return 领取单
     */
    protected abstract UserTakeActivityVO queryNoConsumedTakeActivityOrder(Long activityId, String uId);


    /**
     * 活动信息校验处理，把活动库存、状态、日期、个人参与次数
     *
     * @param req 参与活动请求
     * @param bill    活动账单
     * @return 校验结果
     */
    protected abstract Result checkActivity(PartakeReq req, ActivityBillVO bill);


    /**
     * 扣减活动库存
     *
     * @param req 参与活动请求
     * @return 扣减结果
     */
    protected abstract Result subtractionActivityStock(PartakeReq req);
    /**
     * 扣减活动库存，通过Redis
     *
     * @param uId        用户ID
     * @param activityId 活动号
     * @param stockCount 总库存
     * @return 扣减结果
     */
    protected abstract StockRes subtractionActivityStockByRedis(String uId, Long activityId, Integer stockCount);
    /**
     * 恢复活动库存，通过Redis 【如果非常异常，则需要进行缓存库存恢复，只保证不超卖的特性，所以不保证一定能恢复占用库存，另外最终可以由任务进行补偿库存】
     *
     * @param activityId 活动ID
     * @param tokenKey   分布式 KEY 用于清理
     * @param code       状态
     */
    protected abstract void recoverActivityCacheStockByRedis(Long activityId, String tokenKey, String code);


    /**
     * 领取活动
     *
     * @param req 参与活动请求
     * @param bill    活动账单
     * @param takeId  领取活动ID
     * @return 领取结果
     */
    protected abstract Result grabActivity(PartakeReq req, ActivityBillVO bill, Long takeId);


    /**
     * 封装结果【返回的策略ID，用于继续完成抽奖步骤】
     *
     * @param strategyId 策略ID
     * @param takeId     领取ID
     * @return 封装结果
     */
    private PartakeRes buildPartakeResult(Long strategyId, Long takeId) {
        PartakeRes partakeRes = new PartakeRes(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getInfo());
        partakeRes.setStrategyId(strategyId);
        partakeRes.setTakeId(takeId);
        return partakeRes;
    }
}
