package com.un1ink.infrastructure.po;

import lombok.*;

import java.util.Date;

/**
 * @description: 用户参加活动记录表-分库
 * @author：un1ink
 * @date: 2023/3/28
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class UserTakeActivity {
    /** 自增ID */
    private Long id;

    /** 用户ID */
    private String uId;

    /** 活动参加ID */
    private Long takeId;

    /** 活动ID */
    private Long activityId;

    /** 活动名称 */
    private String activityName;

    /** 活动参与时间 */
    private Date takeDate;

    /** 活动参与次数 */
    private Integer takeCount;

    /** 抽奖状态 0参加但未执行抽奖 1已抽奖 */
    private Integer state;

    /** 防重ID 保证幂等 */
    private String uuid;

    /** 创建时间 */
    private Date createTime;

    /** 更新时间 */
    private Date updateTime;
}
