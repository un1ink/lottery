package com.un1ink.infrastructure.dao;

import com.un1ink.infrastructure.po.RuleTree;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description: 规则树配置 dao
 * @author：un1ink
 * @date: 2023/4/1
 */
@Mapper
public interface IRuleTreeDao {
    /**
     * 规则树查询
     * @param id ID
     * @return   规则树
     */
    RuleTree queryRuleTreeByTreeId(Long id);

    /**
     * 规则树简要信息查询
     * @param treeId 规则树ID
     * @return       规则树
     */
    RuleTree queryTreeSummaryInfo(Long treeId);
}
