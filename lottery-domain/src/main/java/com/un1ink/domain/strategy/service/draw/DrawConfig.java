package com.un1ink.domain.strategy.service.draw;

import com.un1ink.domain.strategy.service.algorithm.IDrawAlgorithm;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.un1ink.common.constants.StrategyMode.ENTIRETY;
import static com.un1ink.common.constants.StrategyMode.SINGLE;

/**
 * @description: 配置抽奖策略信息
 * @author un1ink
 */
public class DrawConfig {
    @Resource
    private IDrawAlgorithm defaultRateRandomDrawAlgorithm;

    @Resource
    private IDrawAlgorithm singleRateRandomDrawAlgorithm;

    protected static Map<Integer, IDrawAlgorithm> drawAlgorithmMap = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        drawAlgorithmMap.put(SINGLE.getCode(), defaultRateRandomDrawAlgorithm);
        drawAlgorithmMap.put(ENTIRETY.getCode(), singleRateRandomDrawAlgorithm);
    }

}
