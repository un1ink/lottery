package com.un1ink.common.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @description:
 * @author：un1ink
 * @date: 2023/3/30
 */

@AllArgsConstructor
@Getter
public enum GrantState {
    /** 初始 */
    INIT(0,"初始"),
    /** 完成 */
    COMPLETE(1, "完成"),
    /** 失败 */
    FAIL(1,"失败");
    private Integer code;
    private String info;
}
