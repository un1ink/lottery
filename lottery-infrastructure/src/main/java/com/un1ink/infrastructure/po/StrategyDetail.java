package com.un1ink.infrastructure.po;

import lombok.*;

import java.math.BigDecimal;

/**
 * @author un1ink
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class StrategyDetail {
    /**
     * 自增ID
     */
    private String id;

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

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String updateTime;
}
