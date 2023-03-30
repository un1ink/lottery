package com.un1ink.application.process;

import com.un1ink.application.process.req.DrawProcessReq;
import com.un1ink.application.process.res.DrawProcessRes;

/**
 * @description: 抽奖活动流程编排接口
 * @author：un1ink
 * @date: 2023/3/30
 */
public interface IActivityProcess {
    /**
     * @param req 抽奖请求
     * @return 抽奖结果
     */
    DrawProcessRes doDrawProcess(DrawProcessReq req);
}
