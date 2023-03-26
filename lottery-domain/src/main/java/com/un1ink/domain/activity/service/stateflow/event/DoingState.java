package com.un1ink.domain.activity.service.stateflow.event;

import com.un1ink.common.Result;
import com.un1ink.common.constants.ActivityState;
import com.un1ink.common.constants.ResponseCode;
import com.un1ink.domain.activity.service.stateflow.AbstractState;
import org.springframework.stereotype.Component;


/**
 * @description: 活动运行中
 * @author：un1ink
 * @date: 2023/3/26
 */
@Component
public class DoingState extends AbstractState {

    @Override
    public Result arraignment(Long activityId, Enum<ActivityState> currentState) {
        return Result.buildResult(ResponseCode.UN_ERROR, "活动中不可提审");
    }

    @Override
    public Result checkPass(Long activityId, Enum<ActivityState> currentState) {
        return Result.buildResult(ResponseCode.UN_ERROR, "活动中不可审核通过");
    }

    @Override
    public Result checkRefuse(Long activityId, Enum<ActivityState> currentState) {
        return Result.buildResult(ResponseCode.UN_ERROR, "活动中不可审核拒绝");
    }

    @Override
    public Result checkRevoke(Long activityId, Enum<ActivityState> currentState) {
        return Result.buildResult(ResponseCode.UN_ERROR, "活动中不可撤销审核");
    }

    @Override
    public Result close(Long activityId, Enum<ActivityState> currentState) {
        boolean isSuccess = activityRepository.alterStatus(activityId, currentState, ActivityState.CLOSE);
        return isSuccess ? Result.buildResult(ResponseCode.SUCCESS, "活动关闭成功") : Result.buildErrorResult("活动状态变更失败");
    }

    @Override
    public Result open(Long activityId, Enum<ActivityState> currentState) {
        return Result.buildResult(ResponseCode.UN_ERROR, "活动中不可开启");
    }

    @Override
    public Result doing(Long activityId, Enum<ActivityState> currentState) {
        return Result.buildResult(ResponseCode.UN_ERROR, "活动中不可重复执行");
    }

}
