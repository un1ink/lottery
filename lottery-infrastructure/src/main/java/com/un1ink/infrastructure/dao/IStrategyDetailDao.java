package com.un1ink.infrastructure.dao;

import com.un1ink.infrastructure.po.StrategyDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author un1ink
 */
@Mapper
public interface IStrategyDetailDao {
    /**
     * 查询策略表详细配置
     * @param strategyId 策略ID
     * @return           返回结果
     */
    List<StrategyDetail> queryStrategyList(Long strategyId);

    /**
     * 查询无库存奖品
     *
     * @param strategyId 策略id
     * @return 无库存奖品id列表*/
    List<String> queryNoStockStrategyAwardList(Long strategyId);

    /**
     * 扣减库存
     * @param strategyDetailReq 策略ID、奖品ID
     * @return                  返回结果
     */
    int deductStock(StrategyDetail strategyDetailReq);

    /**
     * 插入策略配置组
     *
     * @param list 策略配置组
     */
    void insertList(List<StrategyDetail> list);

    List<StrategyDetail> queryStrategyDetailList(Long strategyId);
}
