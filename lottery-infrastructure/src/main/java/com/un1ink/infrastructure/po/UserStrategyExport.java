package com.un1ink.infrastructure.po;

import lombok.*;

import java.util.Date;

/**
 * @description: 用户抽奖结果-分库分表
 * @author：un1ink
 * @date: 2023/3/28
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserStrategyExport {

    /** 自增ID */
    private Long id;

    /** 用户ID */
    private String uId;

    /** 活动ID */
    private Long activityId;

    /** 订单ID */
    private Long orderId;

    /** 策略ID */
    private Long strategyId;

    /** 策略方式（1:单项概率、2:总体概率） */
    private Integer strategyMode;

    /** 发放奖品方式 */
    private Integer grantType;

    /** 发奖时间 */
    private Date grantDate;

    /** 发奖状态 */
    private Integer grantState;

    /** 发奖ID */
    private String awardId;

    /** 奖品类型（1:文字描述、2:兑换码、3:优惠券、4:实物奖品） */
    private Integer awardType;

    /** 奖品名称 */
    private String awardName;

    /** 奖品内容 */
    private String awardContent;

    /** 防重ID */
    private String uuid;

    /** 创建时间 */
    private Date createTime;

    /** 更新时间 */
    private Date updateTime;

}
