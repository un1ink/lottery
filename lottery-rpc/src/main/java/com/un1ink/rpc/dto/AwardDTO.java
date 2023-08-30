package com.un1ink.rpc.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @description: 奖品信息，DTO对象，用于RPC层传输数据。
 * @author：un1ink
 * @date: 2023/4/2
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AwardDTO implements Serializable {

    /** 用户ID */
    private String userId;

    /** 活动ID */
    private Long activityId;

    /** 奖品ID */
    private String awardId;

    /** 奖品类型（1:文字描述、2:兑换码、3:优惠券、4:实物奖品） */
    private Integer awardType;

    /** 奖品名称 */
    private String awardName;

    /** 奖品内容「描述、奖品码、sku」*/
    private String awardContent;

    /** 策略方式（1:单项概率、2:总体概率 */
    private Integer strategyMode;

    /** 发放奖品方式（1:即时、2:定时[含活动结束]、3:人工） */
    private Integer grantType;

}
