package com.un1ink.common.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @description: 发奖状态
 * @author：un1ink
 * @date: 2023/3/26
 */
@AllArgsConstructor
@Getter
public enum AwardState {
    /**
     * 等待发奖
     */
    WAIT(1, "等待发奖"),
    /**
     * 发奖成功
     */
    SUCCESS(2, "发奖成功"),
    /**
     * 发奖失败
     */
    FAIL(3, "发奖失败");

    private Integer code;
    private String info;
}
