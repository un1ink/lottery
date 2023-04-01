package com.un1ink.domain.rule.model.vo;

import lombok.*;

/**
 * @description: 规则树根配置
 * @author：un1ink
 * @date: 2023/3/31
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TreeRootVO {
    /** 规则树id */
    private Long treeId;
    /** 规则树根id */
    private Long treeRootNodeId;
    /** 规则树名称 */
    private String treeName;
}
