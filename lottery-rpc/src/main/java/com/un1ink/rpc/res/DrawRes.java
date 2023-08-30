package com.un1ink.rpc.res;

import com.un1ink.common.Result;
import com.un1ink.rpc.dto.AwardDTO;

import java.io.Serializable;

/**
 * @description: 抽奖结果信息
 * @author：un1ink
 * @date: 2023/4/2
 */
public class DrawRes extends Result implements Serializable {

    /** 奖品信息 */
    private AwardDTO awardDTO;

    public DrawRes(String code, String info) {
        super(code, info);
    }

    public AwardDTO getAwardDTO() {
        return awardDTO;
    }

    public void setAwardDTO(AwardDTO awardDTO) {
        this.awardDTO = awardDTO;
    }

}
