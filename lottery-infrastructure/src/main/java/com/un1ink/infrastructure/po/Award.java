package com.un1ink.infrastructure.po;

import lombok.*;

import java.util.Date;

/**
 * @description: 奖品表实体类-主库
 * @date: 2023/3/26
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Award {

    /** 自增id */
    private Long id;

    /** 奖品id */
    private String awardId;

    /** 奖品类型 */
    private Integer awardType;

    /** 奖品数量 */
    private Integer awardCount;

    /** 奖品名称 */
    private String awardName;

    /** 奖品描述信息 */
    private String awardContent;

    /** 创建时间 */
    private Date createTime;

    /** 更新时间 */
    private Date updateTime;
}
