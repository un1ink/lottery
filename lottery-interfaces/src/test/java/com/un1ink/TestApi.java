package com.un1ink;

import com.alibaba.fastjson.JSON;
import com.un1ink.infrastructure.dao.IActivityDao;
import com.un1ink.infrastructure.po.Activity;
import org.junit.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestApi {

    private Logger logger = LoggerFactory.getLogger(TestApi.class);

    @Resource
    IActivityDao activityDao;

    @Test
    public void test_insert() {
        Activity activity = new Activity();
        activity.setActivityId(200003L);
        activity.setActivityName("测试活动");
        activity.setActivityDesc("仅用于插入数据测试");
        activity.setBeginDateTime(new Date());
        activity.setEndDateTime(new Date());
        activity.setStockCount(100);
        activity.setTakeCount(10);
        activity.setState(0);
        activity.setCreator("xiaofuge");
        activityDao.insert(activity);
    }

    @Test
    public void test_select() {
        Activity activity = activityDao.queryActivityById(200003L);
    }

}
