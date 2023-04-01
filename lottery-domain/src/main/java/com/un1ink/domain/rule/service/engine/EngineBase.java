package com.un1ink.domain.rule.service.engine;

import com.un1ink.common.constants.NodeType;
import com.un1ink.domain.rule.model.aggregates.TreeRuleRich;
import com.un1ink.domain.rule.model.req.DecisionMatterReq;
import com.un1ink.domain.rule.model.res.EngineRes;
import com.un1ink.domain.rule.model.vo.TreeNodeLineVO;
import com.un1ink.domain.rule.model.vo.TreeNodeVO;
import com.un1ink.domain.rule.model.vo.TreeRootVO;
import com.un1ink.domain.rule.service.logic.ILogicFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author：un1ink
 * @date: 2023/3/31
 */
public abstract class EngineBase extends EngineConfig implements IEngineFilter{

    private Logger logger = LoggerFactory.getLogger(EngineBase.class);

    @Override
    public EngineRes process(DecisionMatterReq req) {
        throw new RuntimeException("为实现规则引擎服务");
    }

    protected TreeNodeVO engineDecisionMaker(TreeRuleRich treeRuleRich, DecisionMatterReq req) {
        TreeRootVO treeRootVO = treeRuleRich.getTreeRootVO();
        Map<Long, TreeNodeVO> treeNodeVOMap = treeRuleRich.getTreeNodeMap();
        Long rootNodeId = treeRootVO.getTreeRootNodeId();
        TreeNodeVO treeNodeInfo = treeNodeVOMap.get(rootNodeId);
        while(NodeType.STEM.equals(treeNodeInfo.getNodeType())) {
            String ruleKey = treeNodeInfo.getRuleKey();
            ILogicFilter logicFilter = logicFilterMap.get(ruleKey);
            String matterValue = logicFilter.matterValue(req);
            List<TreeNodeLineVO> list = treeNodeInfo.getTreeNodeLineInfoList();
            Long nextNode = logicFilter.filter(matterValue, treeNodeInfo.getTreeNodeLineInfoList());
            treeNodeInfo = treeNodeVOMap.get(nextNode);
            logger.info("决策树引擎=>{} userId：{} treeId：{} treeNode：{} ruleKey：{} matterValue：{}", treeRootVO.getTreeName(), req.getUserId(), req.getTreeId(), treeNodeInfo.getTreeNodeId(), ruleKey, matterValue);
        }

        return treeNodeInfo;

    }
}
