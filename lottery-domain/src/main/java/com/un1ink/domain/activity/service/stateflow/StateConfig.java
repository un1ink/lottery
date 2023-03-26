package com.un1ink.domain.activity.service.stateflow;

import com.un1ink.common.constants.ActivityState;
import com.un1ink.domain.activity.service.stateflow.event.*;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description:
 * @author：un1ink
 * @date: 2023/3/26
 */
public class StateConfig {

    @Resource
    private ArraignmentState arraignmentState;
    @Resource
    private CloseState closeState;
    @Resource
    private DoingState doingState;
    @Resource
    private EditingState editingState;
    @Resource
    private OpenState openState;
    @Resource
    private PassState passState;
    @Resource
    private RefuseState refuseState;

    protected Map<Enum<ActivityState>, AbstractState> stateGroup = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        stateGroup.put(ActivityState.ARRAIGNMENT, arraignmentState);
        stateGroup.put(ActivityState.CLOSE, closeState);
        stateGroup.put(ActivityState.DOING, doingState);
        stateGroup.put(ActivityState.EDIT, editingState);
        stateGroup.put(ActivityState.OPEN, openState);
        stateGroup.put(ActivityState.PASS, passState);
        stateGroup.put(ActivityState.REFUSE, refuseState);
    }

}
