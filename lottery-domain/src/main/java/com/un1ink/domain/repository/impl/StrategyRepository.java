package com.un1ink.domain.repository.impl;

import com.un1ink.domain.model.aggregates.StrategyRich;
import com.un1ink.domain.repository.IStrategyRepository;
import com.un1ink.infrastructure.dao.IAwardDao;
import com.un1ink.infrastructure.dao.IStrategyDao;
import com.un1ink.infrastructure.dao.IStrategyDetailDao;
import com.un1ink.infrastructure.po.Award;
import com.un1ink.infrastructure.po.Strategy;
import com.un1ink.infrastructure.po.StrategyDetail;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class StrategyRepository implements IStrategyRepository {
    @Resource
    private IStrategyDao strategyDao;
    @Resource
    private IStrategyDetailDao strategyDetailDao;

    @Resource
    private IAwardDao awardDao;

    @Override
    public StrategyRich queryStrategyRich(Long strategyId) {
        Strategy strategy = strategyDao.queryStrategy(strategyId);
        List<StrategyDetail> strategyDetailList = strategyDetailDao.queryStrategyList(strategyId);
        return new StrategyRich(strategyId, strategy, strategyDetailList);
    }

    @Override
    public Award queryAwardInfo(String wardId) {
        return awardDao.queryAwardInfo(wardId);
    }
}
