package com.un1ink;

import com.alibaba.fastjson.JSON;
import com.un1ink.domain.strategy.model.req.DrawReq;
import com.un1ink.domain.strategy.service.draw.IDrawExec;
import com.un1ink.infrastructure.dao.IActivityDao;
import com.un1ink.infrastructure.po.Activity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StrategyAlgorithmTest {

    private Logger logger = LoggerFactory.getLogger(StrategyAlgorithmTest.class);


    @Resource
    private IActivityDao activityDao;

    @Resource
    private IDrawExec drawExec;

    @Test
    public void test_drawExec() {
        drawExec.doDrawExec(new DrawReq("小傅哥", 100001L));
        drawExec.doDrawExec(new DrawReq("小佳佳", 100001L));
        drawExec.doDrawExec(new DrawReq("小蜗牛", 100001L));
        drawExec.doDrawExec(new DrawReq("八杯水", 100001L));
    }

//    @Test
//    public void test_insert() {
//        Activity activity = new Activity();
//        activity.setActivityId(300002L);
//        activity.setActivityName("测试活动");
//        activity.setActivityDesc("仅用于插入数据测试");
//        activity.setBeginDateTime(new Date());
//        activity.setEndDateTime(new Date());
//        activity.setStockCount(100);
//        activity.setTakeCount(10);
//        activity.setState(0);
//        activity.setCreator("xiaofuge");
//        activityDao.insert(activity);
//    }

    @Test
    public void test_select() {
        Activity activity = activityDao.queryActivityById(100001L);
        logger.info("测试结果：{}", JSON.toJSONString(activity));
    }
}
