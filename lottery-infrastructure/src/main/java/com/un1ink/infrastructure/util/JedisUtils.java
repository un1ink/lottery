package com.un1ink.infrastructure.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @description:
 * @author：un1ink
 * @date: 2023/8/30
 */
@Component
public class JedisUtils {
    @Autowired
    private JedisPool jedisPool;

    /**
     * 获取Jedis
     */
    public Jedis getJedis() {

        return jedisPool.getResource();
    }

    /**
     * 关闭Jedis连接
     */
    public void close(Jedis jedis) {
        if(jedis != null) {
            jedis.close();
        }
    }

}