package com.un1ink.domain.activity.model.vo;

import lombok.*;

import java.util.Date;

/**
 * @description: 活动账单【库存、状态、日期、个人参与次数】
 * @author：un1ink
 * @date: 2023/3/29
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ActivityBillVO {
    /** 用户Id */
    private String uId;
    /** 活动Id */
    private Long activityId;
    /** 活动名称 */
    private String activityName;
    /** 策略Id */
    private Long StrategyId;
    /** 库存剩余 */
    private Integer stockSurplusCount;

    /** 活动开始时间 */
    private Date beginDateTime;

    /** 活动结束时间 */
    private Date endDateTime;

    /**
     * 活动状态：1编辑、2提审、3撤审、4通过、5运行(审核通过后worker扫描状态)、6拒绝、7关闭、8开启
     * Constants.ActivityState
     */
    private Integer state;
    /** 用户可参与次数 */
    private Integer takeCount;

    /** 用户剩余参与次数 */
    private Integer userTakeLeftCount;

}
