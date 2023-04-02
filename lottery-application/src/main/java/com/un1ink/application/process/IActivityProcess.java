package com.un1ink.application.process;

import com.un1ink.application.process.req.DrawProcessReq;
import com.un1ink.application.process.res.DrawProcessRes;
import com.un1ink.application.process.res.RuleQuantificationCrowdResult;
import com.un1ink.domain.rule.model.req.DecisionMatterReq;

/**
 * @description: 抽奖活动流程编排接口
 * @author：un1ink
 * @date: 2023/3/30
 */
public interface IActivityProcess {
    /**
     * @param req 抽奖请求
     * @return 抽奖结果
     */
    DrawProcessRes doDrawProcess(DrawProcessReq req);

    /**
     * 规则量化人群，返回可参与的活动ID
     * @param req   规则请求
     * @return      量化结果，用户可以参与的活动ID
     */
    RuleQuantificationCrowdResult doRuleQuantificationCrowd(DecisionMatterReq req);

}
