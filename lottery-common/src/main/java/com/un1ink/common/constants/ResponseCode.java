package com.un1ink.common.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @description: 回复
 * @author：un1ink
 * @date: 2023/3/22
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ResponseCode {
    /** 成功中奖 */
    SUCCESS("0000", "成功"),
    /** 未知失败*/
    UN_ERROR("0001","未知失败"),
    /** 非法参数*/
    ILLEGAL_PARAMETER("0002","非法参数"),
    /** SQL操作无更新 */
    INDEX_DUP("0003","SQL操作无更新"),
    /** SQL操作无更新 */
    NO_UPDATE("0004","SQL操作无更新"),
    /** 未中奖 */
    LOSING_DRAW("D001", "未中奖"),
    /** 量化人群规则执行失败 */
    RULE_ERR("D002", "未中奖"),
    NOT_CONSUMED_TAKE("D003", "未消费活动领取记录"),

    OUT_OF_STOCK("D004", "活动无库存"),
    ERR_TOKEN("D005", "分布式锁失败");

    private String code;
    private String info;
}
