package com.un1ink.domain.rule.service.logic;

import com.un1ink.common.constants.Global;
import com.un1ink.domain.rule.model.vo.TreeNodeLineVO;
import com.un1ink.common.constants.RuleLimitType;

import java.util.List;

/**
 * @description:
 * @author：un1ink
 * @date: 2023/3/31
 */
public abstract class BaseLogic implements ILogicFilter{

    @Override
    public Long filter(String matterValue, List<TreeNodeLineVO> treeNodeLineInfoList) {
        for (TreeNodeLineVO nodeLine : treeNodeLineInfoList) {
            if (decisionLogic(matterValue, nodeLine)) {
                return nodeLine.getNodeIdTo();
            }
        }
        return Global.TREE_NULL_NODE;
    }

    /**
     * @param matterValue
     * @param nodeLineVO
     * @return 比较结果
     */
    private boolean decisionLogic(String matterValue, TreeNodeLineVO nodeLineVO) {
        switch (nodeLineVO.getRuleLimitType()) {
            case RuleLimitType.EQUAL:
                return matterValue.equals(nodeLineVO.getRuleLimitValue());
            case RuleLimitType.GT:
                return Double.parseDouble(matterValue) > Double.parseDouble(nodeLineVO.getRuleLimitValue());
            case RuleLimitType.LT:
                return Double.parseDouble(matterValue) < Double.parseDouble(nodeLineVO.getRuleLimitValue());
            case RuleLimitType.GE:
                return Double.parseDouble(matterValue) >= Double.parseDouble(nodeLineVO.getRuleLimitValue());
            case RuleLimitType.LE:
                return Double.parseDouble(matterValue) <= Double.parseDouble(nodeLineVO.getRuleLimitValue());
            default:
                return false;

        }
    }

}
