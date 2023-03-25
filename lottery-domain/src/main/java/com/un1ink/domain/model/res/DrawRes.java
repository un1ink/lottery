package com.un1ink.domain.model.res;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DrawRes {
    // 用户id
    private String uId;

    // 策略id
    private long strategyId;

    // 奖品id
    private String rewardId;

    // 奖品名称
    private  String awardName;

}
