package com.un1ink.domain.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @description: 获奖信息
 * @author：un1ink
 * @date: 2023/3/25
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DrawAwardInfo {
    /**
     * 奖品id
     */
    private String awardId;

    /**
     * 奖品名称
     */
    private String awardName;
}
