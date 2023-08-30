package com.un1ink;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.security.SecureRandom;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestApi {


    @Resource
    public JedisPool jedisPool;

    @Test
    public void test_idx() {

        Map<Integer, Integer> map = new HashMap<>();

        int HASH_INCREMENT = 0x61c88647;
        int hashCode = 0;
        for (int i = 1; i <= 100; i++) {
            hashCode = i * HASH_INCREMENT + HASH_INCREMENT;
            int idx = hashCode & (128 - 1);

            map.merge(idx, 1, Integer::sum);

            // System.out.println("斐波那契散列：" + idx + " 普通散列：" + (String.valueOf(i).hashCode() & (128 - 1)));
        }

        // System.out.println(map);
    }

    @Test
    public void test_DrawStrategy() {

        List<Map<String, String>> strategyList = new ArrayList<>();

        strategyList.add(new HashMap<String, String>() {{
            put("awardDesc", "一等奖：彩电");
            put("awardId", "10001");
            put("awardCount", "3");
            put("awardRate", "20");
        }});

        strategyList.add(new HashMap<String, String>() {{
            put("awardDesc", "二等奖：冰箱");
            put("awardId", "10002");
            put("awardCount", "5");
            put("awardRate", "30");
        }});

        strategyList.add(new HashMap<String, String>() {{
            put("awardDesc", "三等奖：洗衣机");
            put("awardId", "10003");
            put("awardCount", "10");
            put("awardRate", "50");
        }});

        DrawStrategy drawStrategy = new DrawStrategy();
        drawStrategy.initRateTuple(strategyList);

        for (int i = 0; i < 20; i++) {
            System.out.println("中奖结果：" + drawStrategy.randomDraw());
        }

    }

    @Test
    public void test_random() {
        SecureRandom random = new SecureRandom();

        for (int i = 0; i < 20; i++) {
            System.out.println(random.nextInt(1));
        }


    }

    @Test
    public void contextLoads() {
        System.out.println(jedisPool);
    }


}

