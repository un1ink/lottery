package com.un1ink.domain.service.algorithm.impl;

import com.un1ink.domain.model.vo.AwardRateInfo;
import com.un1ink.domain.service.algorithm.BaseAlgorithm;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.List;

@Component
public class SingleRateRandomDrawAlgorithm extends BaseAlgorithm {


    @Override
    public String randomDraw(Long strategyId, List<String> excludeAwardIds) {
        String[] rateTuple = super.rateTupleMap.get(strategyId);
        assert  rateTuple != null;

        int randomVal = new SecureRandom().nextInt(100) + 1;
        int idx = super.hashIdx(randomVal);
//        System.out.println("randomVal:"+randomVal);
//        System.out.println("idx:" + idx);

        String awardId = rateTuple[idx];
        if(null == awardId || excludeAwardIds.contains(awardId)){
            System.out.println("return 未中奖");
            return null;
        }

        return awardId;
    }
}
