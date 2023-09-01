package com.un1ink.domain.rule.model.req;

import lombok.*;

import java.util.Map;

/**
 * @description: 决策请求
 * @author：un1ink
 * @date: 2023/3/31
 */

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class DecisionMatterReq {

    /** 规则树ID */
    private Long treeId;
    /** 用户ID */
    private String userId;
    /** 决策值() */
    private Map<String, Object> valMap;

}
