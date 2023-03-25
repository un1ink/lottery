package com.un1ink.domain.model.vo;

import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class AwardRateInfo {
    // 奖id
    private String awardId;

    // 中奖概率
    private BigDecimal awardRate;


}
