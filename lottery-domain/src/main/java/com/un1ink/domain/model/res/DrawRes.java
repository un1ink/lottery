package com.un1ink.domain.model.res;
import static com.un1ink.common.constants.DrawState.FAIL;

import com.un1ink.domain.model.vo.DrawAwardInfo;
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
    private Integer drawState = FAIL.getCode();

    /**
     * 奖品名称
     */
    private DrawAwardInfo drawAwardInfo;

}
