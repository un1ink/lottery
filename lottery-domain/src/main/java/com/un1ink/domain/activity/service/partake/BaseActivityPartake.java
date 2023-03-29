package com.un1ink.domain.activity.service.partake;

import com.un1ink.common.Result;
import com.un1ink.domain.activity.model.req.PartakeReq;
import com.un1ink.domain.activity.model.res.PartakeRes;
import com.un1ink.domain.activity.model.vo.ActivityBillVO;
import com.un1ink.common.constants.ResponseCode;
import java.text.AttributedCharacterIterator;

/**
 * @description:
 * @author：un1ink
 * @date: 2023/3/29
 */
public abstract class BaseActivityPartake extends ActivityPartakeSupport implements IActivityPartake{

    @Override
    public PartakeRes doPartake(PartakeReq req) {
        // 查询活动详单
        ActivityBillVO activityBillVO = super.queryActivityBill(req);


        // 活动信息校验处理【活动库存、状态、日期、个人参与次数】
        Result checkResult = this.checkActivity(req, activityBillVO);
        if(!ResponseCode.SUCCESS.getCode().equals(checkResult.getCode())) {
            return new PartakeRes(checkResult.getCode(), checkResult.getInfo());
        }

        // 扣减活动库存【目前为直接对配置库中的 lottery.activity 直接操作表扣减库存，后续优化为Redis扣减】
        Result subtractionActivityResult = this.subtractionActivityStock(req);
        if(!ResponseCode.SUCCESS.getCode().equals(subtractionActivityResult.getCode())) {
            return new PartakeRes(subtractionActivityResult.getCode(), subtractionActivityResult.getInfo());
        }

        // 领取活动信息【个人用户把活动信息写入到用户表】
        Result grabResult = this.grabActivity(req, activityBillVO);
        if(!ResponseCode.SUCCESS.getCode().equals(grabResult.getCode())) {
            return new PartakeRes(grabResult.getCode(), grabResult.getInfo());
        }
        // 封装结果【返回的策略ID，用于继续完成抽奖步骤】

        PartakeRes partakeRes = new PartakeRes(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getInfo());
        partakeRes.setStrategyId(activityBillVO.getStrategyId());
        return partakeRes;
    }


    protected abstract Result checkActivity(PartakeReq req, ActivityBillVO bill);

    protected abstract Result subtractionActivityStock(PartakeReq req);

    protected abstract Result grabActivity(PartakeReq req, ActivityBillVO bill);
}
