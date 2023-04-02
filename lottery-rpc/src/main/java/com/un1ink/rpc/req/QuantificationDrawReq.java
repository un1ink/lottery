package com.un1ink.rpc.req;

import lombok.*;

import java.util.Map;

/**
 * @description:
 * @author：un1ink
 * @date: 2023/4/2
 */

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class QuantificationDrawReq {

    /** 用户ID */
    private String uId;
    /** 规则树ID */
    private Long treeId;
    /** 决策值 */
    private Map<String, Object> valMap;
}
