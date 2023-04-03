package com.un1ink.domain.activity.model.vo;

import com.un1ink.domain.award.model.vo.ShippingAddress;
import lombok.*;

/**
 * @description:
 * @author：un1ink
 * @date: 2023/4/2
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class InvoiceVO {
    /** 用户ID */
    private String uId;

    /** 抽奖单号 ID */
    private Long orderId;

    /** 奖品ID */
    private String awardId;

    /**
     * 奖品类型（1:文字描述、2:兑换码、3:优惠券、4:实物奖品）
     */
    private Integer awardType;

    /** 奖品名称 */
    private String awardName;

    /** 奖品内容「描述、奖品码、sku」 */
    private String awardContent;

    /** 四级送货地址（只有实物类商品需要地址） */
    private ShippingAddress shippingAddress;

    /** 扩展信息，用于一些个性商品发放所需要的透传字段内容 */
    private String extInfo;

}
