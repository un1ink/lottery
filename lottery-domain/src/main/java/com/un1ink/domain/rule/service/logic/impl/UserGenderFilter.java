package com.un1ink.domain.rule.service.logic.impl;

import com.un1ink.domain.rule.model.req.DecisionMatterReq;
import com.un1ink.domain.rule.service.logic.BaseLogic;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author：un1ink
 * @date: 2023/3/31
 */
@Component
public class UserGenderFilter extends BaseLogic {
    @Override
    public String matterValue(DecisionMatterReq decisionMatter) {
        return decisionMatter.getValMap().get("gender").toString();
    }
}
