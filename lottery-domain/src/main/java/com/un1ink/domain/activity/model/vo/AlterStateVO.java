package com.un1ink.domain.activity.model.vo;

import lombok.*;

/**
 * @description: 变更活动状态对象
 * @author：un1ink
 * @date: 2023/3/26
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class AlterStateVO {
    /** 活动ID */
    private Long activityId;

    /** 更新前状态 */
    private Integer beforeState;

    /** 更新后状态 */
    private Integer afterState;
}
