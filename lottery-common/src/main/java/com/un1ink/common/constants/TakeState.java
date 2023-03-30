package com.un1ink.common.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description:
 * @author：un1ink
 * @date: 2023/3/30
 */
@AllArgsConstructor
@Getter
public enum TakeState {
    /** 未使用 */
    NO_USED(0, "未使用"),
    /** 已使用 */
    USED(1, "已使用");
    private Integer code;
    private String info;
}
