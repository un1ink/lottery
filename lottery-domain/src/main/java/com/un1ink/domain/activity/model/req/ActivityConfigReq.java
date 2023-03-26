package com.un1ink.domain.activity.model.req;

import com.un1ink.domain.activity.model.aggregates.ActivityConfigRich;
import lombok.*;

/**
 * @description:
 * @author：un1ink
 * @date: 2023/3/26
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ActivityConfigReq {

    /** 活动ID */
    private Long activityId;

    /** 活动配置信息 */
    private ActivityConfigRich activityConfigRich;


}
