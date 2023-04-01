package com.un1ink.domain.rule.repository;

import com.un1ink.domain.rule.model.aggregates.TreeRuleRich;
import com.un1ink.domain.rule.model.req.DecisionMatterReq;

/**
 * @description:
 * @author：un1ink
 * @date: 2023/3/31
 */
public interface IRuleRepository {

    TreeRuleRich queryTreeRuleRich(Long treeId);
}
