package com.un1ink.domain.activity.model.req;

import lombok.*;

import java.util.Date;

/**
 * @description:
 * @author：un1ink
 * @date: 2023/3/29
 */

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class PartakeReq {

    private String uId;

    private Long activityId;

    private Date partakeDate;

    public PartakeReq(String uId, Long activityId) {
        this.uId = uId;
        this.activityId = activityId;
        this.partakeDate = new Date();
    }
}
