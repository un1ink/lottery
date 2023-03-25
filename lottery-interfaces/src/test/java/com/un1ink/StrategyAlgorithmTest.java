package com.un1ink;

import com.un1ink.domain.model.vo.AwardRateInfo;
import com.un1ink.domain.service.algorithm.impl.DefaultRateRandomDrawAlgorithm;
import com.un1ink.domain.service.algorithm.impl.SingleRateRandomDrawAlgorithm;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StrategyAlgorithmTest {

    @Resource(name = "singleRateRandomDrawAlgorithm")
    private SingleRateRandomDrawAlgorithm singleRateRandomDrawAlgorithm;

    @Resource(name = "defaultRateRandomDrawAlgorithm")
    private DefaultRateRandomDrawAlgorithm defaultRateRandomDrawAlgorithm;

    @Before
    public void init() {
        List<AwardRateInfo> strategyList = new ArrayList<>();
        strategyList.add(new AwardRateInfo("一等奖：MacBook", new BigDecimal("0.05")));
        strategyList.add(new AwardRateInfo("二等奖：iphone", new BigDecimal("0.15")));
        strategyList.add(new AwardRateInfo("三等奖：ipad", new BigDecimal("0.20")));
        strategyList.add(new AwardRateInfo("四等奖：AirPods", new BigDecimal("0.25")));
        strategyList.add(new AwardRateInfo("五等奖：充电宝", new BigDecimal("0.35")));

        Long strategyId = 100001L;

        singleRateRandomDrawAlgorithm.initRateTuple(strategyId, strategyList);
    }

    @Test
    public void test_randomDrawAlgorithm() {

        List<String> excludeAwardIds = new ArrayList<>();
        excludeAwardIds.add("二等奖：iphone");
        excludeAwardIds.add("四等奖：AirPods");

        for (int i = 0; i < 20; i++) {
            System.out.println("中奖结果：" + singleRateRandomDrawAlgorithm.randomDraw(100001L, excludeAwardIds));
        }

    }
}
