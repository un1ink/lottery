package com.un1ink.infrastructure.po;

import lombok.*;

import java.util.Date;

/**
 * @description: 策略表实体类-主库
 * @author un1ink
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Strategy {

    /** 自增id */
    private Long id;

    /** 策略id */
    private Long strategyId;

    /** 策略描述 */
    private String strategyDesc;

    /** 策略方式「1:单项概率、2:总体概率」 */
    private Integer strategyMode;

    /** 发放奖品方式 1:即时、2:定时*/
    private Integer grantType;

    /** 发放奖品时间 */
    private Date grantDate;

    /** 额外信息 */
    private String extInfo;

    /** 创建时间 */
    private Date createTime;

    /** 修改时间 */
    private Date updateTime;
}
