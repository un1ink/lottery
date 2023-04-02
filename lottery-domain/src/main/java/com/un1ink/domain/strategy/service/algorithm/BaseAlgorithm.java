package com.un1ink.domain.strategy.service.algorithm;

import com.un1ink.domain.strategy.model.vo.AwardRateVO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author un1ink
 */
public abstract class BaseAlgorithm implements IDrawAlgorithm{

    private final int HASH_INCREMENT = 0x61c88647;
    private final int RATE_TUPLE_LENGTH = 128;

    protected Map<Long, String[]> rateTupleMap = new ConcurrentHashMap<>();
    protected Map<Long, List<AwardRateVO>> awardRateInfoMap = new ConcurrentHashMap<>();

    @Override
    public void initRateTuple(Long strategyId, List<AwardRateVO> awardRateVOList) {
        awardRateInfoMap.put(strategyId, awardRateVOList);
        String[] rateTuple = rateTupleMap.computeIfAbsent(strategyId, k -> new String[RATE_TUPLE_LENGTH]);

        int cursorVal = 0;
        for(AwardRateVO awardRateVO : awardRateVOList) {
            int rateVal = awardRateVO.getAwardRate().multiply(new BigDecimal(100)).intValue();

            // 循环填充概率范围值
            for(int i = cursorVal + 1; i <= (rateVal + cursorVal); i++){
                rateTuple[hashIdx(i)] = awardRateVO.getAwardId();
            }

            cursorVal += rateVal;

        }
    }

    @Override
    public boolean isExistRateTuple(Long strategyId) {
        return rateTupleMap.containsKey(strategyId);
    }
    protected  int hashIdx(int val) {
        int hashCode = val * HASH_INCREMENT + HASH_INCREMENT;
        return hashCode & (RATE_TUPLE_LENGTH - 1);

    }
}
