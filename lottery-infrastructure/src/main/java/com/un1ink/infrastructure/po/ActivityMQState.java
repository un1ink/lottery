package com.un1ink.infrastructure.po;

import lombok.*;

/**
 * @description:
 * @author：un1ink
 * @date: 2023/4/3
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ActivityMQState {
    /** 用户ID */
    private String uId;
    /** 订单ID */

    private Long orderId;
    /** 消息队列状态*/
    private Integer mqState;
}
