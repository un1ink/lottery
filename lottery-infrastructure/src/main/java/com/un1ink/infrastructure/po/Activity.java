package com.un1ink.infrastructure.po;

import lombok.*;

import java.util.Date;

/**
 * @description: 活动表实体类-主库
 * @date: 2023/3/26
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Activity {

    /** 自增id */
    private Long id;

    /** 活动id */
    private Long activityId;

    /** 活动名称 */
    private String activityName;

    /** 活动描述 */
    private String activityDesc;

    /** 策略id */
    private Long strategyId;

    /** 活动开始时间 */
    private Date beginDateTime;

    /** 活动结束时间 */
    private Date endDateTime;

    /** 初始库存值 */
    private Integer stockCount;

    /** 剩余库存 */
    private Integer stockSurplusCount;


    /** 每人最大参与活动次数 */
    private Integer takeCount;

    /** 活动状态 */
    private Integer state;

    /** 创建者 */
    private String creator;

    /** 创建时间 */
    private Date createTime;

    /** 修改时间 */
    private Date updateTime;
}