package com.un1ink.domain.strategy.model.aggregates;

import com.un1ink.domain.strategy.model.vo.StrategyBriefVO;
import com.un1ink.domain.strategy.model.vo.StrategyDetailBriefVO;

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
    private StrategyBriefVO strategy;
    // 策略细节
    private List<StrategyDetailBriefVO> strategyDetailList;

}
