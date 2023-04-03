package com.un1ink.domain.activity.service.partake;

import com.un1ink.common.Result;
import com.un1ink.common.constants.IdGeneratorMethod;
import com.un1ink.domain.activity.model.req.PartakeReq;
import com.un1ink.domain.activity.model.res.PartakeRes;
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

        // 4. 扣减活动库存【目前为直接对配置库中的 lottery.activity 直接操作表扣减库存，后续优化为Redis扣减】
        Result subtractionActivityResult = this.subtractionActivityStock(req);
        if(!ResponseCode.SUCCESS.getCode().equals(subtractionActivityResult.getCode())) {
            return new PartakeRes(subtractionActivityResult.getCode(), subtractionActivityResult.getInfo());
        }

        // 5. 插入领取活动信息【个人用户把活动信息写入到用户表】
        Long takeId = idGeneratorMap.get(IdGeneratorMethod.SnowFlake).nextId();
        Result grabResult = this.grabActivity(req, activityBillVO, takeId);
        if(!ResponseCode.SUCCESS.getCode().equals(grabResult.getCode())) {
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
