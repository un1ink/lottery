package com.un1ink.infrastructure.dao;

import com.un1ink.infrastructure.po.Strategy;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author un1ink
 */
@Mapper
public interface IStrategyDao {
    /**
     * 查询策略
     *
     * @param strategyId 策略id
     * @return 策略详情
     */
    Strategy queryStrategy(Long strategyId);
}
