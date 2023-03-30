package com.un1ink.domain.activity.model.vo;

import lombok.*;

/**
 * @description: 用户参加活动记录
 * @author：un1ink
 * @date: 2023/3/30
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class UserTakeActivityVO {
    /**
     * 活动ID
     */
    private Long activityId;

    /**
     * 活动领取ID
     */
    private Long takeId;

    /**
     * 策略ID
     */
    private Long strategyId;

    /**
     * 活动单使用状态 0未使用、1已使用
     * Constants.TaskState
     */
    private Integer state;
}
