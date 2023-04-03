package com.un1ink.domain.award.service.goods.impl;

import com.un1ink.common.constants.AwardState;
import com.un1ink.common.constants.GrantState;
import com.un1ink.domain.award.model.req.GoodsReq;
import com.un1ink.domain.award.model.res.DistributionRes;
import com.un1ink.domain.award.service.goods.DistributionBase;
import com.un1ink.domain.award.service.goods.IDistributionGoods;
import org.springframework.stereotype.Component;

import static com.un1ink.common.constants.AwardState.SUCCESS;
import static com.un1ink.common.constants.AwardType.CouponGoods;

/**
 * @description: 优惠卷商品信息
 * @author：un1ink
 * @date: 2023/3/26
 */
@Component
public class CouponGoods extends DistributionBase implements IDistributionGoods {

    @Override
    public DistributionRes doDistribution(GoodsReq req) {

        // 模拟调用优惠券发放接口
        logger.info("模拟调用优惠券发放接口 uId：{} awardContent：{}", req.getUId(), req.getAwardContent());

        // 更新用户领奖结果
        super.updateUserAwardState(req.getUId(), req.getOrderId(), req.getAwardId(), GrantState.COMPLETE.getCode());

        return new DistributionRes(req.getUId(), AwardState.SUCCESS.getCode(), AwardState.SUCCESS.getInfo(), null);

    }

}
