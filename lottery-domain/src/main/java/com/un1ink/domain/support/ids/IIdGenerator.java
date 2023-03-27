package com.un1ink.domain.support.ids;

/**
 * @description: id生成接口
 * @author：un1ink
 * @date: 2023/3/27
 */
public interface IIdGenerator {
    /**
     * 获取id, 目前已经实现以下三种：
     * 1. 雪花算法，用于生成单号
     * 2. 日期算法，用于生成活动编号类，特性是生成数字串短，但指定时间内不可再生
     * 3. 随机算法，用于生成策略id
     */
    long nextId();
}
