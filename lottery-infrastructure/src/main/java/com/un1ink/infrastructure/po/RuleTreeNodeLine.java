package com.un1ink.infrastructure.po;

import lombok.*;

/**
 * @description: 规则树节点连线
 * @author：un1ink
 * @date: 2023/4/1
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class RuleTreeNodeLine {

    /** 主键ID */
    private Long id;
    /** 规则树ID */
    private Long treeId;
    /** 节点From */
    private Long nodeIdFrom;
    /** 节点To */
    private Long nodeIdTo;
    /** 限定类型；1:=;2:>;3:<;4:>=;5<=;6:enum[枚举范围] */
    private Integer ruleLimitType;
    /** 限定值 */
    private String ruleLimitValue;

}
