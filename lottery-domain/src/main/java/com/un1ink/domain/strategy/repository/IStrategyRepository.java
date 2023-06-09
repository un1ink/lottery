package com.un1ink.domain.strategy.repository;

import com.un1ink.domain.activity.model.vo.AwardVO;
import com.un1ink.domain.strategy.model.aggregates.StrategyRich;
import com.un1ink.domain.strategy.model.vo.AwardBriefVO;

import java.util.List;

public interface IStrategyRepository {

    /**
     * 查询策略详情
     * @param strategyId 策略id
     * @return 策略详情信息
     */
    StrategyRich queryStrategyRich(Long strategyId);

    /**
     * 查询获奖信息
     * @param awardId 奖品id
     * @return 奖品信息
     */
    AwardBriefVO queryAwardInfo(String awardId);

    /**
     * 查询无库存奖品
     * @param strategyId 策略id
     * @return 无库存奖品列表
     */
    List<String> queryNoStockStrategyAwardList(Long strategyId);

    /**
     * 扣减库存
     *
     * @param strategyId 策略id
     * @param awardId 奖品id
     * @return 扣减结果
     */
    boolean deductStock(Long strategyId, String awardId);
}
