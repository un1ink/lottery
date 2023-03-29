package com.un1ink.domain.activity.service.partake;

import com.un1ink.domain.activity.model.req.PartakeReq;
import com.un1ink.domain.activity.model.vo.ActivityBillVO;
import com.un1ink.domain.activity.repository.IActivityRepository;

import javax.annotation.Resource;

/**
 * @description:
 * @author：un1ink
 * @date: 2023/3/29
 */
public class ActivityPartakeSupport {
    @Resource
    protected IActivityRepository activityRepository;

    protected ActivityBillVO queryActivityBill(PartakeReq req) {
        return activityRepository.queryActivityBill(req);
    }
}
