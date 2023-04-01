package com.un1ink.domain.rule.model.vo;

import lombok.*;

import java.util.List;

/**
 * @description:
 * @author：un1ink
 * @date: 2023/3/31
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TreeNodeVO {
    /** 规则树ID */
    private Long treeId;
    /** 规则树节点ID */
    private Long treeNodeId;
    /** 节点类型；1内部节点、2叶子节点 */
    private Integer nodeType;
    /** 节点值[nodeType=2]；果实值 */
    private String nodeValue;
    /** 规则Key */
    private String ruleKey;
    /** 规则描述 */
    private String ruleDesc;
    /** 节点链路 */
    private List<TreeNodeLineVO> treeNodeLineInfoList;
}
