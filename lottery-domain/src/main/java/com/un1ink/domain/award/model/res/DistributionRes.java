package com.un1ink.domain.award.model.res;

import lombok.*;

/**
 * @description: 商品配送结果
 * @author：un1ink
 * @date: 2023/3/26
 */

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class DistributionRes {
    /** 用户id */
    private String uId;

    /** 用户id */
    private Integer code;

    /** 配送信息 */
    private String info;

    /** 结算单id */
    private String statementId;

}
