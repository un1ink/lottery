package com.un1ink.domain.strategy.service.algorithm.impl;

import com.un1ink.domain.strategy.model.vo.AwardRateVO;
import com.un1ink.domain.strategy.service.algorithm.BaseAlgorithm;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: 随机抽奖算法(内部处理超卖问题)
 * @date: 2023/3/27
 */
@Component
public class DefaultRateRandomDrawAlgorithm extends BaseAlgorithm {

    @Override
    public String randomDraw(Long strategyId, List<String> excludeAwardIds) {
        BigDecimal differenceDenominator = BigDecimal.ZERO;

        List<AwardRateVO> differenceAwardRateList = new ArrayList<>();
        List<AwardRateVO> awardRateIntervalValList = awardRateInfoMap.get(strategyId);

        // 排除掉不在抽奖范围的奖品ID
        for(AwardRateVO awardRateVO : awardRateIntervalValList) {
            String awardId = awardRateVO.getAwardId();
            if(excludeAwardIds.contains(awardId)) {
                continue;
            }
            differenceAwardRateList.add(awardRateVO);
            differenceDenominator = differenceDenominator.add(awardRateVO.getAwardRate());
        }

        // 前置判断
        if (differenceAwardRateList.size() == 0) {
            return "";
        }
        if (differenceAwardRateList.size() == 1) {
            return differenceAwardRateList.get(0).getAwardId();
        }

        SecureRandom secureRandom = new SecureRandom();
        int randomVal = secureRandom.nextInt(100) + 1;

        String awardId = "";
        int cursorVal = 0;
        for(AwardRateVO awardRateVO : differenceAwardRateList) {
            int rateVal = awardRateVO.getAwardRate().divide(differenceDenominator, 2, BigDecimal.ROUND_UP).multiply(new BigDecimal(100)).intValue();
            if(randomVal <= (cursorVal + rateVal)) {
                awardId = awardRateVO.getAwardId();
                break;
            }
            cursorVal += rateVal;
        }

        return awardId;
    }
}
