package com.un1ink;

import com.alibaba.fastjson.JSON;
import com.un1ink.common.constants.AwardState;
import com.un1ink.common.constants.DrawState;
import com.un1ink.domain.award.model.req.GoodsReq;
import com.un1ink.domain.award.model.res.DistributionRes;
import com.un1ink.domain.award.service.factory.DistributionGoodsFactory;
import com.un1ink.domain.award.service.goods.IDistributionGoods;
import com.un1ink.domain.strategy.model.req.DrawReq;
import com.un1ink.domain.strategy.model.res.DrawRes;
import com.un1ink.domain.strategy.model.vo.DrawAwardInfo;
import com.un1ink.domain.strategy.service.draw.IDrawExec;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
/**
 * @description:
 * @author：un1ink
 * @date: 2023/3/26
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class DistributionGoodsTest {
    Logger logger = LoggerFactory.getLogger(DistributionGoodsTest.class);
    @Resource
    private IDrawExec drawExec;
    @Resource
    private DistributionGoodsFactory distributionGoodsFactory;
    @Test
    public void test_award() {

        for(int i = 0; i < 10; i++){
            // 执行抽奖

            DrawRes drawRes = drawExec.doDrawExec(new DrawReq("小傅哥", 100001L,"temp_uuid"));
            // 判断抽奖结果
            Integer drawState = drawRes.getDrawState();
            if (DrawState.FAIL.getCode().equals(drawState)) {
                logger.info("DrawAwardInfo is null:{}", AwardState.FAIL.getInfo());
                continue;
            }

            // 封装发奖参数，orderId：2109313442431 为模拟ID，需要在用户参与领奖活动时生成
            DrawAwardInfo drawAwardInfo = drawRes.getDrawAwardInfo();
            GoodsReq goodsReq = GoodsReq.builder()
                    .uId(drawRes.getUId())
                    .orderId("2109313442431")
                    .awardId(drawAwardInfo.getAwardId())
                    .awardName(drawAwardInfo.getAwardName())
                    .awardContent(drawAwardInfo.getAwardContent())
                    .build();

            // 根据 awardType 从抽奖工厂中获取对应的发奖服务
            IDistributionGoods distributionGoodsService = distributionGoodsFactory.getDistributionGoodsService(drawAwardInfo.getAwardType());
            DistributionRes distributionRes = distributionGoodsService.doDistribution(goodsReq);

            logger.info("测试结果：{}", JSON.toJSONString(distributionRes));

        }

    }


}
