package com.un1ink.domain.model.req;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DrawReq {
    // 用户id
    private String uId;

    // 策略id
    private long strategyId;
}
