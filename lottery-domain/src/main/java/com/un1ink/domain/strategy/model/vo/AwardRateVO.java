package com.un1ink.domain.strategy.model.vo;

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
public class AwardRateVO {
    /**
     * 奖品id
     */
    private String awardId;

    /**
     * 中奖概率
     */
    private BigDecimal awardRate;


}
