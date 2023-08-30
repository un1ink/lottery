package com.un1ink.infrastructure;

import com.un1ink.infrastructure.util.JedisUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * @description:
 * @author：un1ink
 * @date: 2023/8/30
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class JedisTest {

    private Logger logger = LoggerFactory.getLogger(JedisTest.class);

    @Autowired
    private JedisUtils jedisUtils;

    @Autowired
    public JedisPool jedisPool;

    @Test
    public void contextLoads() {
        System.out.println(jedisPool);
    }

    @Test
    public void test_set() {
        String key = "t1";
        String value = "v1";
        Jedis jedis = jedisUtils.getJedis();
        //设置key为t1，value为v1，过期时间为20秒
        jedis.setex(key, 60, value);
        // 释放jedis连接
        jedisUtils.close(jedis);
    }


}
