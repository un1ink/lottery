package com.un1ink.domain.activity.model.vo;

import lombok.*;

/**
 * @description:
 * @author：un1ink
 * @date: 2023/4/10
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ActivityPartakeRecordVO {
    /** 用户ID */
    private String uId;
    /** activity 活动ID */
    private Long activityId;
    /** 库存 */
    private Integer stockCount;
    /** activity 库存剩余 */
    private Integer stockSurplusCount;

}
