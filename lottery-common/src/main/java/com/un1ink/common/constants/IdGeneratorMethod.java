package com.un1ink.common.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description:
 * @author：un1ink
 * @date: 2023/3/27
 */

@AllArgsConstructor
@Getter
public enum IdGeneratorMethod {
    /**
     * 雪花算法
     */
    SnowFlake,
    /**
     * 日期短编码算法
     */
    ShortCode,
    /**
     * 随机算法
     */
    RandomNumeric;


}
