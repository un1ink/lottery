package com.un1ink.domain.rule.model.res;

import lombok.*;

/**
 * @description: 决策结果
 * @author：un1ink
 * @date: 2023/3/31
 */

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class EngineRes {
    /** 执行结果 */
    private boolean isSuccess;
    /** 用户ID */
    private String userId;
    /** 规则树ID */
    private Long treeId;
    /** 叶子节点ID */
    private Long nodeId;
    /** 叶子节点值 */
    private String nodeValue;
}
