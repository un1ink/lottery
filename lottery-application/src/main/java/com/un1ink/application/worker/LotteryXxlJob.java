package com.un1ink.application.worker;

import com.alibaba.fastjson.JSON;
import com.un1ink.application.mq.producer.KafkaProducer;
import com.un1ink.common.Result;
import com.un1ink.common.constants.ActivityState;
import com.un1ink.common.constants.MQState;
import com.un1ink.domain.activity.model.vo.ActivityVO;
import com.un1ink.domain.activity.model.vo.InvoiceVO;
import com.un1ink.domain.activity.repository.IActivityMQStateRepository;
import com.un1ink.domain.activity.service.deploy.IActivityDeploy;
import com.un1ink.domain.activity.service.partake.IActivityPartake;
import com.un1ink.domain.activity.service.stateflow.IStateHandler;
import com.un1ink.middleware.db.router.strategy.IDBRouterStrategy;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

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
    private IActivityPartake activityPartake;


    @Resource
    private IStateHandler stateHandler;

    @Resource
    private IDBRouterStrategy dbRouter;

    @Resource
    private KafkaProducer kafkaProducer;


    @Resource
    private IActivityMQStateRepository activityMQStateRepository;

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

    @XxlJob("lotteryOrderMQStateJob")
    public void lotteryOrderMQStateJob() throws Exception {
        String jobParam = XxlJobHelper.getJobParam();
        if (null == jobParam) {
            logger.info("扫描用户抽奖奖品发放MQ状态错误 params is null");
            return;
        }

        String[] params = jobParam.split(",");
        logger.info("扫描用户抽奖奖品发放MQ状态开始 params：{}", JSON.toJSONString(params));
        if(params.length == 0){
            logger.info("扫描用户抽奖奖品发放MQ状态结束 params is null");
            return;
        }

        for (String param : params) {
            int dbCount = Integer.parseInt(param);
            if (dbCount > dbRouter.dbCount()) {
                logger.info("扫描用户抽奖奖品发放MQ状态[Table = 2*4] 结束 dbCount not exist");
                continue;
            }

            List<InvoiceVO> invoiceVOList = activityPartake.scanInvoiceMqState(dbCount);
            for (InvoiceVO invoiceVO : invoiceVOList) {
                logger.info("invoiceVO:{}"+JSON.toJSONString(invoiceVO));
                ListenableFuture<SendResult<String, Object>> future = kafkaProducer.sendLotteryInvoice(invoiceVO);
            }
        }
        logger.info("扫描用户抽奖奖品发放MQ状态完成 param：{}", JSON.toJSONString(params));
    }




}
