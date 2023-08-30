package com.un1ink.rpc;

import com.un1ink.rpc.req.DrawReq;
import com.un1ink.rpc.req.QuantificationDrawReq;
import com.un1ink.rpc.res.DrawRes;

/**
 * @description:
 * @author：un1ink
 * @date: 2023/4/2
 */
public interface ILotteryActivityBooth {
    /**
     * 指定活动抽奖
     * @param drawReq 请求参数
     * @return        抽奖结果
     */
    DrawRes doDraw(DrawReq drawReq);

    /**
     * 量化人群抽奖
     * @param quantificationDrawReq 请求参数
     * @return                      抽奖结果
     */
    DrawRes doQuantificationDraw(QuantificationDrawReq quantificationDrawReq);

    int testRpc(String message);

}
