package com.un1ink.application;


import com.alibaba.fastjson.JSON;
import com.un1ink.application.process.IActivityProcess;
import com.un1ink.application.process.req.DrawProcessReq;
import com.un1ink.application.process.res.DrawProcessRes;
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
 * @date: 2023/3/30
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivityProcessTest {

    private Logger logger = LoggerFactory.getLogger(ActivityProcessTest.class);

    @Resource
    private IActivityProcess activityProcess;

    @Test
    public void test_doDrawProcess() {
        for(int i = 0; i < 1; i++) {
            DrawProcessReq req = new DrawProcessReq();
            req.setUId("un1ink_0830_" + i);
            req.setActivityId(100001L);
            DrawProcessRes drawProcessResult = activityProcess.doDrawProcess(req);

            logger.info("请求入参：{}", JSON.toJSONString(req));
            logger.info("测试结果：{}", JSON.toJSONString(drawProcessResult));
        }

    }

}