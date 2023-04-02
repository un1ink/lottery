package com.un1ink.application.process.res;

import com.un1ink.common.Result;

/**
 * @description:
 * @author：un1ink
 * @date: 2023/4/2
 */
public class RuleQuantificationCrowdResult extends Result {

    /** 活动ID */
    private Long activityId;

    public RuleQuantificationCrowdResult(String code, String info) {
        super(code, info);
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

}
