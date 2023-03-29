package com.un1ink.infrastructure.po;

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
public class UserTakeActivityCount {
    /**
     * 自增ID
     */
    private Long id;
    /**
     * 用户ID
     */
    private String uId;
    /**
     * 活动ID
     */
    private Long activityId;
    /**
     * 可领取次数
     */
    private Integer totalCount;
    /**
     * 已领取次数
     */
    private Integer leftCount;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
}
