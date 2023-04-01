package com.un1ink.infrastructure.po;

import lombok.*;

/**
 * @description: 规则树节点
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
    /** 节点类型；1子叶、2果实 */
    private Integer nodeType;
    /** 节点值[nodeType=2]；果实值 */
    private String nodeValue;
    /** 规则Key */
    private String ruleKey;
    /** 规则描述 */
    private String ruleDesc;

}
