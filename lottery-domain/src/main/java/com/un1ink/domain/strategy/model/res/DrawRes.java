package com.un1ink.domain.strategy.model.res;
import com.un1ink.common.constants.DrawState;

import com.un1ink.domain.strategy.model.vo.DrawAwardVO;
import lombok.*;


/**
 * @author un1ink
 */
@NoArgsConstructor
@Getter
@AllArgsConstructor
public class DrawRes {
    // 用户id
    /**
     * 用户id
     */
    private String uId;

    /**
     * 策略id
     */
    private long strategyId;


    /**
     * 奖品id
     */
    private Integer drawState = DrawState.FAIL.getCode();

    /**
     * 奖品名称
     */
    private DrawAwardVO drawAwardVO;

}
