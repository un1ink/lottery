package com.un1ink.infrastructure.po;

import lombok.*;

/**
 * @description: 规则树节点信息-主库
 * @author：un1ink
 * @date: 2023/4/1
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class RuleTreeNode {
    /** 主键ID */
    private Long id;

    /** 规则树ID */
    private Long treeId;

    /** 节点类型；1非叶子节点(进行决策判断)、2叶子(指向活动) */
    private Integer nodeType;

    /** (叶子所指向)活动id */
    private String nodeValue;

    /** 规则Key */
    private String ruleKey;

    /** 规则描述 */
    private String ruleDesc;

}
