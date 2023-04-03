package com.un1ink.domain.activity.model.res;

import com.un1ink.common.Result;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @description:
 * @author：un1ink
 * @date: 2023/3/29
 */

@Getter
@Setter
public class PartakeRes extends Result {
    /**
     * 策略id
     */
    private Long strategyId;
    /**
     * 策略id
     */
    private Long takeId;
    public PartakeRes(String code, String info) {
        super(code, info);
    }

}
