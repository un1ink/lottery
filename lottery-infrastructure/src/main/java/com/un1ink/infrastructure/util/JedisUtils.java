package com.un1ink.infrastructure.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

@Configuration
@Component
public class JedisUtils {
    private static Jedis jedis;

    @Value("${redis.host}")
    private String redisHost;

    @Value("${redis.port}")
    private int redisPort;

    static {
        // todo 从配置文件中读取
        jedis = new Jedis("localhost", 6379);
    }

    public static Jedis getJedis() {
        return jedis;
    }

    public long getAndIncr(String key, long delta) {
        long currentValue = jedis.incrBy(key, delta);
        return currentValue;
    }




}
