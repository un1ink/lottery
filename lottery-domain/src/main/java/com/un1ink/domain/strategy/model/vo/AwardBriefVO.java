package com.un1ink.domain.strategy.model.vo;

import lombok.*;

/**
 * @description:
 * @author：un1ink
 * @date: 2023/3/26
 */

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class AwardBriefVO {

    /** 奖品ID */
    private String awardId;

    /** 奖品类型（1:文字描述、2:兑换码、3:优惠券、4:实物奖品） */
    private Integer awardType;

    /** 奖品名称 */
    private String awardName;

    /** 奖品内容「描述、奖品码、sku」 */
    private String awardContent;

}
