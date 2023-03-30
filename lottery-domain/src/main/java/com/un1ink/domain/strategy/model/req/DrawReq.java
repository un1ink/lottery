package com.un1ink.domain.strategy.model.req;

import lombok.*;

/**
 * @description: 抽奖请求对象
 * @author un1ink
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DrawReq {
    /** 用户id */
    private String uId;

    /** 策略id */
    private long strategyId;
    /** 防重id */
    private String uuid;

    public DrawReq(String uId, long strategyId){
        this.strategyId = strategyId;
        this.uId = uId;
    }

}
