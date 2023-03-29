package com.un1ink.common.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @description: 回复
 * @author：un1ink
 * @date: 2023/3/22
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ResponseCode {
    SUCCESS("0000", "成功"),
    UN_ERROR("0001","未知失败"),
    ILLEGAL_PARAMETER("0002","非法参数"),
    INDEX_DUP("0003","主键冲突"),
    NO_UPDATE("0004","SQL操作无更新");

    private String code;
    private String info;
}
