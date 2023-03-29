package com.un1ink.dao;

import com.alibaba.fastjson.JSON;
import com.un1ink.domain.support.ids.IIdGenerator;
import com.un1ink.infrastructure.dao.IUserStrategyExportDao;
import com.un1ink.infrastructure.po.UserStrategyExport;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.un1ink.common.constants.IdGeneratorMethod;
import com.un1ink.common.constants.StrategyMode;
import com.un1ink.common.constants.AwardType;
import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;


/**
 * @description:
 * @author：un1ink
 * @date: 2023/3/28
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserStrategyExportDaoTest {

    private Logger logger = LoggerFactory.getLogger(UserStrategyExportDaoTest.class);

    @Resource
    private IUserStrategyExportDao userStrategyExportDao;

    @Resource
    private Map<IdGeneratorMethod, IIdGenerator> idGeneratorMap;

    @Test
    public void test_insert() {
        UserStrategyExport userStrategyExport = new UserStrategyExport();
        userStrategyExport.setUId("Uhdgkw766120d");
        userStrategyExport.setActivityId(idGeneratorMap.get(IdGeneratorMethod.ShortCode).nextId());
        userStrategyExport.setOrderId(idGeneratorMap.get(IdGeneratorMethod.SnowFlake).nextId());
        userStrategyExport.setStrategyId(idGeneratorMap.get(IdGeneratorMethod.RandomNumeric).nextId());
        userStrategyExport.setStrategyMode(StrategyMode.SINGLE.getCode());
        userStrategyExport.setGrantType(1);
        userStrategyExport.setGrantDate(new Date());
        userStrategyExport.setGrantState(1);
        userStrategyExport.setAwardId("1");
        userStrategyExport.setAwardType(AwardType.RedeemCodeGoods.getCode());
        userStrategyExport.setAwardName("兑换码");
        userStrategyExport.setAwardContent("奖品描述");
        userStrategyExport.setUuid(String.valueOf(userStrategyExport.getOrderId()));

        userStrategyExportDao.insert(userStrategyExport);
    }

    @Test
    public void test_select() {
        UserStrategyExport userStrategyExport = userStrategyExportDao.queryUserStrategyExportByUId("Uhdgkw766120d");
        logger.info("测试结果：{}", JSON.toJSONString(userStrategyExport));
    }

}
