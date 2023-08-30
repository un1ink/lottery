package com.un1ink.infrastructure.po;

import lombok.*;

import java.util.Date;

/**
 * @description: 规则树信息-主库
 * @author：un1ink
 * @date: 2023/4/1
 */

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class RuleTree {

    /** 主键ID */
    private Long id;

    /** 规则树名称 */
    private String treeName;

    /** 规则树描述 */
    private String treeDesc;

    /** 规则树根ID */
    private Long treeRootNodeId;

    /** 创建时间 */
    private Date createTime;

    /** 更新时间 */
    private Date updateTime;
}
