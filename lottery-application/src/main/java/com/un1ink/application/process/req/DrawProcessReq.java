package com.un1ink.application.process.req;

import lombok.*;

/**
 * @description:
 * @author：un1ink
 * @date: 2023/3/30
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DrawProcessReq {

    /** 用户id */
    private String uId;

    /** 活动id */
    private Long activityId;
}
