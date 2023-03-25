package com.un1ink.domain.repository;

import com.un1ink.domain.model.aggregates.StrategyRich;
import com.un1ink.infrastructure.po.Award;

public interface IStrategyRepository {
    StrategyRich queryStrategyRich(Long strategyId);

    Award queryAwardInfo(String wardId);
}
