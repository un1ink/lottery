package com.un1ink.domain.strategy.service.draw;

import com.un1ink.domain.strategy.model.aggregates.StrategyRich;
import com.un1ink.domain.strategy.model.vo.AwardBriefVO;
import com.un1ink.domain.strategy.repository.IStrategyRepository;

import javax.annotation.Resource;

/**
 * @description: 抽奖数据支持，提供通用服务
 * @author：un1ink
 * @date: 2023/3/25
 */
public class DrawStrategySupport extends DrawConfig{
    @Resource
    protected IStrategyRepository strategyRepository;

    /**
     * @param strategyId 策略id
     * @return 策略配置信息
     */
    protected StrategyRich queryStrategyRich(Long strategyId) {
        return strategyRepository.queryStrategyRich(strategyId);
    }
    /**
     * @param  awardId 奖品id
     * @return 奖品详情*/
    protected AwardBriefVO queryAwardInfoByAwardId(String awardId){
        return strategyRepository.queryAwardInfo(awardId);

    }
}
