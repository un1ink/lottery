package com.un1ink.interfaces;

import com.alibaba.fastjson.JSON;
import com.un1ink.rpc.ILotteryActivityBooth;
import com.un1ink.rpc.req.DrawReq;
import com.un1ink.rpc.req.QuantificationDrawReq;
import com.un1ink.rpc.res.DrawRes;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @description:
 * @author：un1ink
 * @date: 2023/4/2
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LotteryActivityBoothTest {

    private Logger logger = LoggerFactory.getLogger(LotteryActivityBoothTest.class);

    @Resource
    private ILotteryActivityBooth lotteryActivityBooth;

    @Test

    public void test(){
        int epoch = 1;
        for(int i = 0; i < epoch; i++){
            String uId = "un1ink_"+i;
            test_doDraw(uId);
            test_doQuantificationDraw(uId);
        }
    }
//    @Test
    public void test_doDraw(String uId) {
        DrawReq drawReq = new DrawReq();
        drawReq.setUId(uId);
        drawReq.setActivityId(100001L);
        DrawRes drawRes = lotteryActivityBooth.doDraw(drawReq);
        logger.info("请求参数：{}", JSON.toJSONString(drawReq));
        logger.info("测试结果：{}", JSON.toJSONString(drawRes));

    }

//    @Test
    public void test_doQuantificationDraw(String uId) {
        QuantificationDrawReq req = new QuantificationDrawReq();
        req.setUId(uId
        );
        req.setTreeId(2110081902L);
        req.setValMap(new HashMap<String, Object>() {{
            put("gender", "man");
            put("age", "18");
        }});

        DrawRes drawRes = lotteryActivityBooth.doQuantificationDraw(req);
        logger.info("请求参数：{}", JSON.toJSONString(req));
        logger.info("测试结果：{}", JSON.toJSONString(drawRes));
    }

}
