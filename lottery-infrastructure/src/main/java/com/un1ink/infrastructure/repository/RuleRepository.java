package com.un1ink.infrastructure.repository;

import com.un1ink.common.constants.NodeType;
import com.un1ink.domain.rule.model.aggregates.TreeRuleRich;
import com.un1ink.domain.rule.model.vo.TreeNodeLineVO;
import com.un1ink.domain.rule.model.vo.TreeNodeVO;
import com.un1ink.domain.rule.model.vo.TreeRootVO;
import com.un1ink.domain.rule.repository.IRuleRepository;
import com.un1ink.infrastructure.dao.IRuleTreeNodeDao;
import com.un1ink.infrastructure.dao.IRuleTreeNodeLineDao;
import com.un1ink.infrastructure.dao.IRuleTreeDao;
import com.un1ink.infrastructure.po.RuleTree;
import com.un1ink.infrastructure.po.RuleTreeNode;
import com.un1ink.infrastructure.po.RuleTreeNodeLine;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author：un1ink
 * @date: 2023/4/1
 */

@Repository
public class RuleRepository implements IRuleRepository {
    @Resource
    private IRuleTreeDao ruleTreeDao;

    @Resource
    private IRuleTreeNodeDao ruleNodeDao;

    @Resource
    private IRuleTreeNodeLineDao ruleTreeNodeLineDao;

    @Override
    public TreeRuleRich queryTreeRuleRich(Long treeId) {
        // 规则树
        RuleTree ruleTree = ruleTreeDao.queryRuleTreeByTreeId(treeId);
        TreeRootVO treeRootVO = new TreeRootVO();
        treeRootVO.setTreeId(ruleTree.getId());
        treeRootVO.setTreeName(ruleTree.getTreeName());
        treeRootVO.setTreeRootNodeId(ruleTree.getTreeRootNodeId());

        // 树节点和连线
        Map<Long, TreeNodeVO> treeNodeVOMap = new HashMap<>();
        List<RuleTreeNode> ruleTreeNodes = ruleNodeDao.queryRuleTreeNodeList(treeId);
        for(RuleTreeNode treeNode : ruleTreeNodes) {
            List<TreeNodeLineVO> treeNodeLineVOList = new ArrayList<>();
            // 内部节点
            if (NodeType.STEM.equals(treeNode.getNodeType())) {
                RuleTreeNodeLine ruleTreeNodeLine = new RuleTreeNodeLine();
                ruleTreeNodeLine.setTreeId(treeId);
                ruleTreeNodeLine.setNodeIdFrom(treeNode.getId());
                List<RuleTreeNodeLine> ruleTreeNodeLineList = ruleTreeNodeLineDao.queryRuleTreeNodeLineList(ruleTreeNodeLine);
                for (RuleTreeNodeLine nodeLine : ruleTreeNodeLineList) {
                    TreeNodeLineVO treeNodeLineVO = new TreeNodeLineVO();
                    treeNodeLineVO.setNodeIdFrom(nodeLine.getNodeIdFrom());
                    treeNodeLineVO.setNodeIdTo(nodeLine.getNodeIdTo());
                    treeNodeLineVO.setRuleLimitType(nodeLine.getRuleLimitType());
                    treeNodeLineVO.setRuleLimitValue(nodeLine.getRuleLimitValue());
                    treeNodeLineVOList.add(treeNodeLineVO);
                }

            }
            TreeNodeVO treeNodeVO = new TreeNodeVO();
            treeNodeVO.setTreeId(treeNode.getTreeId());
            treeNodeVO.setTreeNodeId(treeNode.getId());
            treeNodeVO.setNodeType(treeNode.getNodeType());
            treeNodeVO.setNodeValue(treeNode.getNodeValue());
            treeNodeVO.setRuleKey(treeNode.getRuleKey());
            treeNodeVO.setRuleDesc(treeNode.getRuleDesc());
            treeNodeVO.setTreeNodeLineInfoList(treeNodeLineVOList);

            treeNodeVOMap.put(treeNodeVO.getTreeNodeId(), treeNodeVO);
        }

        TreeRuleRich treeRuleRich = new TreeRuleRich();
        treeRuleRich.setTreeRootVO(treeRootVO);
        treeRuleRich.setTreeNodeMap(treeNodeVOMap);

        return treeRuleRich;
    }
}
