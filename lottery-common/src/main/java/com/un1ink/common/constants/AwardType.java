package com.un1ink.common.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 奖品类型
 * @author：un1ink
 * @date: 2023/3/26
 */
@AllArgsConstructor
@Getter
public enum AwardType {
    /**
     * 兑换码
     */
    RedeemCodeGoods(1, "兑换码"),
    /**
     * 优惠券
     */
    CouponGoods(2, "优惠券"),
    /**
     * 实物奖品
     */
    PhysicalGoods(3, "实物奖品");

    private Integer code;
    private String Info;

}
