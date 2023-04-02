package com.un1ink.application.process.res;

import com.un1ink.common.Result;
import com.un1ink.domain.strategy.model.vo.DrawAwardVO;
import lombok.Getter;
import lombok.Setter;

/**
 * @description: 活动抽奖结果
 * @author：un1ink
 * @date: 2023/3/30
 */
@Setter
@Getter
public class DrawProcessRes extends Result {
    private DrawAwardVO drawAwardVO;

    public DrawProcessRes(String code, String info, DrawAwardVO drawAwardVO){
        super(code, info);
        this.drawAwardVO = drawAwardVO;
    }

    public DrawProcessRes(String code, String info){
        super(code, info);
    }


}
