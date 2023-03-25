package com.un1ink.infrastructure.dao;

import com.un1ink.infrastructure.po.StrategyDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IStrategyDetailDao {
    List<StrategyDetail> queryStrategyList(Long strategyId);
}
