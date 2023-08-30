package com.un1ink.rpc.req;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @description: 抽奖请求
 * @author：un1ink
 * @date: 2023/4/2
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DrawReq implements Serializable {

    /** 用户ID */
    private String uId;
    /** 活动ID */
    private Long activityId;
}
