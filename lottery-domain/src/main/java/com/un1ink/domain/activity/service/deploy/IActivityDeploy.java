package com.un1ink.domain.activity.service.deploy;

import com.un1ink.domain.activity.model.req.ActivityConfigReq;

/**
 * @description:
 * @author：un1ink
 * @date: 2023/3/26
 */
public interface IActivityDeploy {
    /**
     * 创建活动信息
     * @param req 活动配置信息
     */
    void createActivity(ActivityConfigReq req);

    /**
     * 修改活动信息
     *
     * @param req 活动配置信息
     */
    void updateActivity(ActivityConfigReq req);
}
