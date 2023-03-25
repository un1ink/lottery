package com.un1ink.common.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 抽奖策略模式
 * @author：un1ink
 * @date: 2023/3/25
 */
@AllArgsConstructor
@Getter
public enum StrategyMode {

    // A奖品抽空，BC保持原有中奖概率
    SINGLE(1,"单项概率"),
    // A奖品抽空，BC按比例扩增
    ENTIRETY(2,"总体概率");
    private Integer code;
    private String info;
}
