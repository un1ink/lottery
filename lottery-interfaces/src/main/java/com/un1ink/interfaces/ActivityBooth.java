package com.un1ink.interfaces;

import com.un1ink.common.Result;
import com.un1ink.common.constants.ResponseCode;
import com.un1ink.infrastructure.dao.IActivityDao;
import com.un1ink.infrastructure.po.Activity;
import com.un1ink.rpc.IActivityBooth;
import com.un1ink.rpc.dto.ActivityDto;
import com.un1ink.rpc.req.ActivityReq;
import com.un1ink.rpc.res.ActivityRes;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;

@Service
public class ActivityBooth implements IActivityBooth {

    @Resource
    private IActivityDao activityDao;

    @Override
    public ActivityRes queryActivityById(ActivityReq req) {

        Activity activity = activityDao.queryActivityById(req.getActivityId());

        ActivityDto activityDto = new ActivityDto();
        activityDto.setActivityId(activity.getActivityId());
        activityDto.setActivityName(activity.getActivityName());
        activityDto.setActivityDesc(activity.getActivityDesc());
        activityDto.setBeginDateTime(activity.getBeginDateTime());
        activityDto.setEndDateTime(activity.getEndDateTime());
        activityDto.setStockCount(activity.getStockCount());
        activityDto.setTakeCount(activity.getTakeCount());

        return new ActivityRes(new Result(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getInfo()), activityDto);
    }

}
