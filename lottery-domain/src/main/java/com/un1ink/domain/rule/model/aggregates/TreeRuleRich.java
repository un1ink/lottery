package com.un1ink.domain.rule.model.aggregates;

import com.un1ink.domain.rule.model.vo.TreeNodeVO;
import com.un1ink.domain.rule.model.vo.TreeRootVO;
import lombok.*;

import java.util.Map;

/**
 * @description: 规则树聚合
 * @author：un1ink
 * @date: 2023/3/31
 */

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class TreeRuleRich {
    /** 树根信息 */
    private TreeRootVO treeRootVO;
    /** 树节点ID -> 子节点 */
    private Map<Long, TreeNodeVO> treeNodeMap;

}
