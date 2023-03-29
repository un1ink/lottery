package com.un1ink.domain.activity.service.partake;

import com.un1ink.domain.activity.model.req.PartakeReq;
import com.un1ink.domain.activity.model.res.PartakeRes;

/**
 * @description: 抽奖活动参与接口
 * @author：un1ink
 * @date: 2023/3/26
 */
public interface IActivityPartake {
    /**
     * 参与活动
     *
     * @param req 参加活动请求
     * @return 活动结果
     */
    PartakeRes doPartake(PartakeReq req);
}
