package com.un1ink.application.worker;

import com.alibaba.fastjson.JSON;
import com.un1ink.common.Result;
import com.un1ink.common.constants.ActivityState;
import com.un1ink.domain.activity.model.vo.ActivityVO;
import com.un1ink.domain.activity.service.deploy.IActivityDeploy;
import com.un1ink.domain.activity.service.stateflow.IStateHandler;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author：un1ink
 * @date: 2023/4/4
 */

@Component
public class LotteryXxlJob {

    private final Logger logger = LoggerFactory.getLogger(LotteryXxlJob.class);

    @Resource
    private IActivityDeploy activityDeploy;

    @Resource
    private IStateHandler stateHandler;


    @XxlJob("lotteryActivityStatePass2DoingJob")
    public void lotteryActivityStatePass2DoingJob() throws Exception {
        logger.info("扫描活动状态(pass -> doing) Begin");

        List<ActivityVO> activityVOList = activityDeploy.scanToDoActivityList(0L, ActivityState.PASS.getCode());
        if (activityVOList.isEmpty()){
            logger.info("扫描活动状态 End 暂无符合需要扫描的活动列表");
            return;
        }
        while (!activityVOList.isEmpty()) {
            for (ActivityVO activityVO : activityVOList) {
                Integer state = activityVO.getState();
                if (ActivityState.PASS.getCode().equals(state)) {
                    Result res = stateHandler.doing(activityVO.getActivityId(), ActivityState.PASS);
                    logger.info("扫描活动状态为活动中 结果：{} activityId：{} activityName：{} creator：{}", JSON.toJSONString(res), activityVO.getActivityId(), activityVO.getActivityName(), activityVO.getCreator());
                }
            }

            ActivityVO activityVO =  activityVOList.get(activityVOList.size() - 1);
            activityVOList = activityDeploy.scanToDoActivityList(activityVO.getActivityId(), ActivityState.PASS.getCode());
        }

        logger.info("扫描活动状态(pass -> doing) End");

    }

    @XxlJob("lotteryActivityStateDoing2CloseJob")
    public void lotteryActivityStateDoing2CloseJob() throws Exception {
        logger.info("扫描活动状态(doing -> close) Begin ");

        List<ActivityVO> activityVOList = activityDeploy.scanToDoActivityList(0L, ActivityState.DOING.getCode());
        if (activityVOList.isEmpty()){
            logger.info("扫描活动状态 End 暂无符合需要扫描的活动列表");
            return;
        }

        while (!activityVOList.isEmpty()) {
            for (ActivityVO activityVO : activityVOList) {
                Integer state = activityVO.getState();
                if (ActivityState.DOING.getCode().equals(state) && activityVO.getEndDateTime().before(new Date())) {
                    Result res = stateHandler.close(activityVO.getActivityId(), ActivityState.DOING);
                    logger.info("扫描活动状态为关闭 结果：{} activityId：{} activityName：{} creator：{}", JSON.toJSONString(res), activityVO.getActivityId(), activityVO.getActivityName(), activityVO.getCreator());
                }
            }

            ActivityVO activityVO =  activityVOList.get(activityVOList.size() - 1);
            activityVOList = activityDeploy.scanToDoActivityList(activityVO.getId(), ActivityState.DOING.getCode());
        }
        logger.info("扫描活动状态(doing -> close) End");

    }

}
