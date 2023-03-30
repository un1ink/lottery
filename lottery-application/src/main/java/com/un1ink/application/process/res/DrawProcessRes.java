package com.un1ink.application.process.res;

import com.un1ink.common.Result;
import com.un1ink.domain.strategy.model.vo.DrawAwardInfo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

/**
 * @description: 活动抽奖结果
 * @author：un1ink
 * @date: 2023/3/30
 */
@Setter
@Getter
public class DrawProcessRes extends Result {
    private DrawAwardInfo drawAwardInfo;

    public DrawProcessRes(String code, String info, DrawAwardInfo drawAwardInfo){
        super(code, info);
        this.drawAwardInfo = drawAwardInfo;
    }

    public DrawProcessRes(String code, String info){
        super(code, info);
    }


}
