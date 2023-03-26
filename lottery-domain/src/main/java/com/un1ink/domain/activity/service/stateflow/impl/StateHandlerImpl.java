package com.un1ink.domain.activity.service.stateflow.impl;

import com.un1ink.common.Result;
import com.un1ink.common.constants.ActivityState;
import com.un1ink.domain.activity.service.stateflow.IStateHandler;
import com.un1ink.domain.activity.service.stateflow.StateConfig;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author：un1ink
 * @date: 2023/3/26
 */
@Service
public class StateHandlerImpl extends StateConfig implements IStateHandler {

    @Override
    public Result arraignment(Long activityId, Enum<ActivityState> currentStatus) {
        beforeAlter(currentStatus);
        return stateGroup.get(currentStatus).arraignment(activityId, currentStatus);
    }

    @Override
    public Result checkPass(Long activityId, Enum<ActivityState> currentStatus) {
        beforeAlter(currentStatus);
        return stateGroup.get(currentStatus).checkPass(activityId, currentStatus);
    }

    @Override
    public Result checkRefuse(Long activityId, Enum<ActivityState> currentStatus) {
        beforeAlter(currentStatus);
        return stateGroup.get(currentStatus).checkRefuse(activityId, currentStatus);
    }

    @Override
    public Result checkRevoke(Long activityId, Enum<ActivityState> currentStatus) {
        beforeAlter(currentStatus);
        return stateGroup.get(currentStatus).checkRevoke(activityId, currentStatus);
    }

    @Override
    public Result close(Long activityId, Enum<ActivityState> currentStatus) {
        beforeAlter(currentStatus);
        return stateGroup.get(currentStatus).close(activityId, currentStatus);
    }

    @Override
    public Result open(Long activityId, Enum<ActivityState> currentStatus) {
        beforeAlter(currentStatus);

        System.out.println("当前状态："+((ActivityState)currentStatus).getInfo());
        return stateGroup.get(currentStatus).open(activityId, currentStatus);
    }

    @Override
    public Result doing(Long activityId, Enum<ActivityState> currentStatus) {
        beforeAlter(currentStatus);
        return stateGroup.get(currentStatus).doing(activityId, currentStatus);
    }

    public void beforeAlter(Enum<ActivityState> currentStatus){
        System.out.printf(((ActivityState)currentStatus).getInfo());
    }

}
