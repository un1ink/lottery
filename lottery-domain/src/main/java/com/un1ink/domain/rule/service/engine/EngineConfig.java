package com.un1ink.domain.rule.service.engine;

import com.un1ink.domain.rule.service.logic.ILogicFilter;
import com.un1ink.domain.rule.service.logic.impl.UserAgeFilter;
import com.un1ink.domain.rule.service.logic.impl.UserGenderFilter;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description:
 * @author：un1ink
 * @date: 2023/3/31
 */
public class EngineConfig {
    protected static Map<String, ILogicFilter> logicFilterMap = new ConcurrentHashMap<>();

    @Resource
    private UserAgeFilter userAgeFilter;

    @Resource
    private UserGenderFilter userGenderFilter;

    @PostConstruct
    public void init() {
        logicFilterMap.put("userAge", userAgeFilter);
        logicFilterMap.put("userGender", userGenderFilter);
    }
}
