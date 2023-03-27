package com.un1ink.domain.support.ids.policy;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;
import com.un1ink.domain.support.ids.IIdGenerator;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @description: 雪花算法实现(基于)hutool
 * @author：un1ink
 * @date: 2023/3/27
 */

@Component
public class SnowFlake implements IIdGenerator {

    private Snowflake snowflake;

    @PostConstruct
    public void init() {
        long workerId;
        try {
            workerId = NetUtil.ipv4ToLong(NetUtil.getLocalhostStr());
        } catch (Exception e) {
            workerId = NetUtil.getLocalhostStr().hashCode();
        }

        workerId = workerId >> 16 & 31;
        long dataCenterId = 1L;
        snowflake = IdUtil.createSnowflake(workerId, dataCenterId);

    }

    @Override
    public long nextId() {
        return snowflake.nextId();
    }
}
