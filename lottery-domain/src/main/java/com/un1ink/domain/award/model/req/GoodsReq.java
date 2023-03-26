package com.un1ink.domain.award.model.req;

import com.un1ink.domain.award.model.vo.ShippingAddress;
import lombok.*;

/**
 * @description: 奖品发货信息
 * @author：un1ink
 * @date: 2023/3/26
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class GoodsReq {

    /** 用户ID */
    private String uId;

    /** 抽奖单号 ID */
    private String orderId;

    /** 奖品ID */
    private String awardId;

    /**
     * 奖品名称
     */
    private String awardName;

    /**
     * 奖品内容「描述、奖品码、sku」
     */
    private String awardContent;

    /** 送货地址（只有实物类商品需要地址） */
    private ShippingAddress shippingAddress;

    /** 扩展信息，用于一些个性商品发放所需要的透传字段内容 */
    private String extInfo;

}
