package com.un1ink.common.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @description:
 * @author：un1ink
 * @date: 2023/4/2
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum MQState {
    INIT(0, "初始"),
    COMPLETE(1, "完成"),
    FAIL(2, "失败");

    private Integer code;
    private String info;
}
