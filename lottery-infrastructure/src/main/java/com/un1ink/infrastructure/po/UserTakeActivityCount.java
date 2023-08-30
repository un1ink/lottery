package com.un1ink.infrastructure.po;

import lombok.*;

import java.util.Date;

/**
 * @description: 用户剩余参加活动次数表-分库
 * @author：un1ink
 * @date: 2023/3/29
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class UserTakeActivityCount {

    /** 自增ID */
    private Long id;

    private String uId;

    /** 活动ID */
    private Long activityId;

    /** 可参加次数 */
    private Integer totalCount;

    /** 已参加次数 */
    private Integer leftCount;

    /** 创建时间 */
    private Date createTime;

    /** 更新时间 */
    private Date updateTime;
}
