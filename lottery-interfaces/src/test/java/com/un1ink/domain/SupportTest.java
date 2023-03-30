package com.un1ink.domain;

import com.un1ink.common.constants.IdGeneratorMethod;
import com.un1ink.domain.support.ids.IIdGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @description:
 * @author：un1ink
 * @date: 2023/3/30
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SupportTest {

    private Logger logger = LoggerFactory.getLogger(SupportTest.class);

    @Resource
    private Map<IdGeneratorMethod, IIdGenerator> idGeneratorMap;

    @Test
    public void test_ids() {
        logger.info("雪花算法策略，生成ID：{}", idGeneratorMap.get(IdGeneratorMethod.SnowFlake).nextId());
        logger.info("日期算法策略，生成ID：{}", idGeneratorMap.get(IdGeneratorMethod.ShortCode).nextId());
        logger.info("随机算法策略，生成ID：{}", idGeneratorMap.get(IdGeneratorMethod.RandomNumeric).nextId());
    }

}
