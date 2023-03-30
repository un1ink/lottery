package com.un1ink.infrastructure.po;

import lombok.*;

import java.util.Date;

/**
 * @description: 用户领取活动表
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
    /**
     * 自增ID
     */
    private Long id;
    /**
     * 用户ID
     */
    private String uId;
    /**
     * 活动领取ID
     */
    private Long takeId;
    /**
     * 活动ID
     */
    private Long activityId;
    /**
     * 活动名称
     */
    private String activityName;
    /**
     * 活动参与时间
     */
    private Date takeDate;
    /**
     * 参与次数
     */
    private Integer takeCount;
    /**
     * 活动单使用 0未使用 1已使用
     */
    private Integer state;
    /**
     * 防重ID
     */
    private String uuid;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
}
