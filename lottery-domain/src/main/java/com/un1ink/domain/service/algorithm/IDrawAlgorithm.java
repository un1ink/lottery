package com.un1ink.domain.service.algorithm;

import com.un1ink.domain.model.vo.AwardRateInfo;

import java.util.List;

public interface IDrawAlgorithm {
    void initRateTuple(Long strategyId, List<AwardRateInfo> awardRateInfoList);

    boolean isExistRateTuple(Long strategyId);

    String randomDraw(Long strategyId, List<String> excludeAwardIds);

}
