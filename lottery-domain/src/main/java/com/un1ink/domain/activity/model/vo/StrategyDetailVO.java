package com.un1ink.domain.activity.model.vo;

import lombok.*;

import java.math.BigDecimal;

/**
 * @description: 策略详细信息
 * @author：un1ink
 * @date: 2023/3/26
 */

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class StrategyDetailVO {
    /**
     * 策略ID
     */
    private Long strategyId;

    /**
     * 奖品ID
     */
    private String awardId;

    /**
     * 奖品名称
     */
    private String awardName;

    /**
     * 奖品库存
     */
    private Integer awardCount;

    /**
     * 奖品剩余库存
     */
    private Integer awardSurplusCount;

    /**
     * 中奖概率
     */
    private BigDecimal awardRate;


}
