package com.un1ink.domain.strategy.model.vo;

import lombok.*;

import java.util.Date;

/**
 * @description: 策略简要信息
 * @author：un1ink
 * @date: 2023/3/26
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class StrategyBriefVO {

    /**
     * 策略ID
     */
    private Long strategyId;

    /**
     * 策略描述
     */
    private String strategyDesc;

    /**
     * 策略方式「1:单项概率、2:总体概率」
     */
    private Integer strategyMode;

    /**
     * 发放奖品方式「1:即时、2:定时[含活动结束]、3:人工」
     */
    private Integer grantType;

    /**
     * 发放奖品时间
     */
    private Date grantDate;
}
