package com.un1ink.domain.award.service.goods.impl;

import com.un1ink.domain.award.model.req.GoodsReq;
import com.un1ink.domain.award.model.res.DistributionRes;
import com.un1ink.domain.award.service.goods.DistributionBase;
import com.un1ink.domain.award.service.goods.IDistributionGoods;
import org.springframework.stereotype.Component;

import static com.un1ink.common.constants.AwardState.SUCCESS;
import static com.un1ink.common.constants.AwardType.PhysicalGoods;

/**
 * @description: 实物放货类商品
 * @author：un1ink
 * @date: 2023/3/26
 */
@Component
public class PhysicalGoods extends DistributionBase implements IDistributionGoods {
    @Override
    public DistributionRes doDistribution(GoodsReq req) {
        logger.info("模拟调用实物发奖 uId：{} awardContent：{}", req.getUId(), req.getAwardContent());
        super.updateUserAwardState(req.getUId(), req.getOrderId(), req.getAwardId(),SUCCESS.getCode(), SUCCESS.getInfo() );
        return new DistributionRes(req.getUId(), SUCCESS.getCode(), SUCCESS.getInfo(), null);
    }

    @Override
    public Integer getDistributionGoodName() {
        return PhysicalGoods.getCode();
    }
}