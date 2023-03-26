package com.un1ink.domain.strategy.service.algorithm;

import com.un1ink.domain.strategy.model.vo.AwardRateInfo;

import java.util.List;

/**
 * @author un1ink
 */
public interface IDrawAlgorithm {
    void initRateTuple(Long strategyId, List<AwardRateInfo> awardRateInfoList);

    boolean isExistRateTuple(Long strategyId);

    String randomDraw(Long strategyId, List<String> excludeAwardIds);

}
