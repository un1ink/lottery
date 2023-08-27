package com.un1ink.domain.rule.service.engine.impl;

import com.un1ink.domain.rule.model.aggregates.TreeRuleRich;
import com.un1ink.domain.rule.model.req.DecisionMatterReq;
import com.un1ink.domain.rule.model.res.EngineRes;
import com.un1ink.domain.rule.model.vo.TreeNodeVO;
import com.un1ink.domain.rule.repository.IRuleRepository;
import com.un1ink.domain.rule.service.engine.EngineBase;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @description:
 * @author：un1ink
 * @date: 2023/3/31
 */
@Service
public class RuleEngineHandle extends EngineBase {
    @Resource
    private IRuleRepository ruleRepository;

    @Override
    public EngineRes process(DecisionMatterReq req) {

        TreeRuleRich treeRuleRich = ruleRepository.queryTreeRuleRich(req.getTreeId());

        if (null == treeRuleRich) {
            throw new RuntimeException("Tree Rule is null!");
        }

        Map<Long, TreeNodeVO> map = treeRuleRich.getTreeNodeMap();

        // 决策节点
        TreeNodeVO treeNodeInfo = engineDecisionMaker(treeRuleRich, req);

        // 决策结果
        return new EngineRes(true, req.getUserId(), treeNodeInfo.getTreeId(), treeNodeInfo.getTreeNodeId(), treeNodeInfo.getNodeValue());
    }
}
