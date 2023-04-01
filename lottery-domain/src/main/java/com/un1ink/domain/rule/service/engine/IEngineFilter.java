package com.un1ink.domain.rule.service.engine;

import com.un1ink.domain.rule.model.req.DecisionMatterReq;
import com.un1ink.domain.rule.model.res.EngineRes;

/**
 * @description: 规则过滤器引擎
 * @author：un1ink
 * @date: 2023/3/31
 */
public interface IEngineFilter {

    /**
     * 规则过滤器接口
     *
     * @param matter      规则决策物料
     * @return            规则决策结果
     */
    EngineRes process(final DecisionMatterReq matter);
}
