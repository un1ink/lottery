package com.un1ink.domain.activity.model.aggregates;

import com.un1ink.domain.activity.model.vo.ActivityVO;
import com.un1ink.domain.activity.model.vo.AwardVO;
import com.un1ink.domain.activity.model.vo.StrategyVO;
import lombok.*;

import java.util.List;

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
public class ActivityConfigRich {

    /** 活动配置 */
    private ActivityVO activity;

    /** 策略配置(含策略明细) */
    private StrategyVO strategy;

    /** 奖品配置 */
    private List<AwardVO> awardList;

}
