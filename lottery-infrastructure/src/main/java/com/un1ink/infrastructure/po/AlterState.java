package com.un1ink.infrastructure.po;

import lombok.*;

/**
 * @description:
 * @author：un1ink
 * @date: 2023/3/26
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class AlterState {
    /** 活动ID */
    private Long activityId;

    /** 更新前状态 */
    private Integer beforeState;

    /** 更新后状态 */
    private Integer afterState;
}
