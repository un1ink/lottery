package com.un1ink.domain.support.ids.policy;

import com.un1ink.domain.support.ids.IIdGenerator;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

/**
 * @description: 工具类生成(apache.common)
 * @author：un1ink
 * @date: 2023/3/27
 */
@Component
public class RandomNumeric implements IIdGenerator {
    @Override
    public long nextId() {
        return Long.parseLong(RandomStringUtils.randomNumeric(11));
    }
}
