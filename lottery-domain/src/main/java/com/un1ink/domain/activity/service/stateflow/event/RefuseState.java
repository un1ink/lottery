package com.un1ink.domain.activity.service.stateflow.event;

import com.un1ink.common.Result;
import com.un1ink.common.constants.ActivityState;
import com.un1ink.common.constants.ResponseCode;
import com.un1ink.domain.activity.service.stateflow.AbstractState;
import org.springframework.stereotype.Component;

/**
 * @description: 审核拒绝状态
 * @author：un1ink
 * @date: 2023/3/26
 */
@Component
public class RefuseState extends AbstractState {

    @Override
    public Result arraignment(Long activityId, Enum<ActivityState> currentState) {
        return Result.buildResult(ResponseCode.UN_ERROR, "已审核状态不可重复提审");
    }

    @Override
    public Result checkPass(Long activityId, Enum<ActivityState> currentState) {
        return Result.buildResult(ResponseCode.UN_ERROR, "已审核状态不可重复审核");
    }

    @Override
    public Result checkRefuse(Long activityId, Enum<ActivityState> currentState) {
        return Result.buildResult(ResponseCode.UN_ERROR, "活动审核拒绝不可重复审核");
    }

    @Override
    public Result checkRevoke(Long activityId, Enum<ActivityState> currentState) {
        boolean isSuccess = activityRepository.alterStatus(activityId, currentState, ActivityState.EDIT);
        return isSuccess ? Result.buildResult(ResponseCode.SUCCESS, "撤销审核完成") : Result.buildErrorResult("活动状态变更失败");
    }

    @Override
    public Result close(Long activityId, Enum<ActivityState> currentState) {
        boolean isSuccess = activityRepository.alterStatus(activityId, currentState, ActivityState.CLOSE);
        return isSuccess ? Result.buildResult(ResponseCode.SUCCESS, "活动审核关闭完成") : Result.buildErrorResult("活动状态变更失败");
    }

    @Override
    public Result open(Long activityId, Enum<ActivityState> currentState) {
        return Result.buildResult(ResponseCode.UN_ERROR, "非关闭活动不可开启");
    }

    @Override
    public Result doing(Long activityId, Enum<ActivityState> currentState) {
        return Result.buildResult(ResponseCode.UN_ERROR, "审核拒绝不可执行活动为进行中");
    }

}
