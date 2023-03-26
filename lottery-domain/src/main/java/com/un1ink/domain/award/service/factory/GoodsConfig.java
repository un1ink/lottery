package com.un1ink.domain.award.service.factory;

import com.un1ink.domain.award.service.goods.IDistributionGoods;
import com.un1ink.domain.award.service.goods.impl.CouponGoods;
import com.un1ink.domain.award.service.goods.impl.PhysicalGoods;
import com.un1ink.domain.award.service.goods.impl.RedeemCodeGoods;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.un1ink.common.constants.AwardType.*;
/**
 * @description: 各类发奖奖品配置类
 * @author：un1ink
 * @date: 2023/3/26
 */
public class GoodsConfig {

    protected static Map<Integer, IDistributionGoods> goodsMap = new ConcurrentHashMap<>();

    @Resource
    private RedeemCodeGoods redeemCodeGoods;

    @Resource
    private CouponGoods couponGoods;

    @Resource
    private PhysicalGoods physicalGoods;

    @PostConstruct
    public void init(){
        goodsMap.put(RedeemCodeGoods.getCode(), redeemCodeGoods);
        goodsMap.put(CouponGoods.getCode(), couponGoods);
        goodsMap.put(PhysicalGoods.getCode(), physicalGoods);
    }

}
