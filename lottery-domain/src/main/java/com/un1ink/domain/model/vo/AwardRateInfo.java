package com.un1ink.domain.model.vo;

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
public class AwardRateInfo {
    /**
     * 奖品id
     */
    private String awardId;

    /**
     * 中奖概率
     */
    private BigDecimal awardRate;


}
