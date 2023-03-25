package com.un1ink.common.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 抽奖状态
 * @author：un1ink
 * @date: 2023/3/25
 */
@AllArgsConstructor
@Getter
public enum DrawState {
    FAIL(0,"未中奖"),
    SUCCESS(1,"已中奖"),

    Cover(2,"保底奖");

    private Integer code;
    private String info;
}
