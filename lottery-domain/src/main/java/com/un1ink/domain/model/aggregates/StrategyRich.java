package com.un1ink.domain.model.aggregates;

import com.un1ink.infrastructure.po.Strategy;
import com.un1ink.infrastructure.po.StrategyDetail;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class StrategyRich {
    // 策略id
    private Long strategyId;
    // 策略配置
    private Strategy strategy;
    // 策略细节
    private List<StrategyDetail> strategyDetailList;

}
