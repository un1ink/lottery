package com.un1ink.domain.support.ids;

import com.un1ink.common.constants.IdGeneratorMethod;
import com.un1ink.domain.support.ids.policy.RandomNumeric;
import com.un1ink.domain.support.ids.policy.ShortCode;
import com.un1ink.domain.support.ids.policy.SnowFlake;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author：un1ink
 * @date: 2023/3/27
 */
@Configuration
public class IdGeneratorConfig {
    /**
     * 创建id生成策略对象，根据策略生成id
     */
    @Bean
    public Map<IdGeneratorMethod, IIdGenerator> idGenerator(SnowFlake snowFlake, ShortCode shortCode, RandomNumeric randomNumeric){
        Map<IdGeneratorMethod, IIdGenerator> idGeneratorMap = new HashMap<>(8);
        idGeneratorMap.put(IdGeneratorMethod.SnowFlake, snowFlake);
        idGeneratorMap.put(IdGeneratorMethod.ShortCode, shortCode);
        idGeneratorMap.put(IdGeneratorMethod.RandomNumeric, randomNumeric);
        return idGeneratorMap;
    }
}
