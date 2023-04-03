package com.un1ink.application.process.impl;

import com.un1ink.application.mq.producer.KafkaProducer;
import com.un1ink.application.process.IActivityProcess;
import com.un1ink.application.process.req.DrawProcessReq;
import com.un1ink.application.process.res.DrawProcessRes;
import com.un1ink.application.process.res.RuleQuantificationCrowdResult;
import com.un1ink.common.Result;
import com.un1ink.common.constants.*;
import com.un1ink.domain.activity.model.req.PartakeReq;
import com.un1ink.domain.activity.model.res.PartakeRes;
import com.un1ink.domain.activity.model.vo.DrawOrderVO;
import com.un1ink.domain.activity.model.vo.InvoiceVO;
import com.un1ink.domain.activity.repository.IActivityMQStateRepository;
import com.un1ink.domain.activity.service.partake.IActivityPartake;
import com.un1ink.domain.rule.model.req.DecisionMatterReq;
import com.un1ink.domain.rule.model.res.EngineRes;
import com.un1ink.domain.rule.service.engine.IEngineFilter;
import com.un1ink.domain.strategy.model.req.DrawReq;
import com.un1ink.domain.strategy.model.res.DrawRes;
import com.un1ink.domain.strategy.model.vo.DrawAwardVO;
import com.un1ink.domain.strategy.service.draw.IDrawExec;
import com.un1ink.domain.support.ids.IIdGenerator;
import org.apache.tomcat.util.digester.Rule;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @description:
 * @author：un1ink
 * @date: 2023/3/30
 */

@Service
public class ActivityProcessImpl implements IActivityProcess {

    @Resource
    private IActivityPartake activityPartake;

    @Resource
    IActivityMQStateRepository activityMQStateRepository;

    @Resource
    private IDrawExec drawExec;

    @Resource
    private Map<IdGeneratorMethod, IIdGenerator> idGeneratorMap;

    @Resource
    private IEngineFilter engineFilter;

    @Resource
    private KafkaProducer kafkaProducer;


    @Override
    public DrawProcessRes doDrawProcess(DrawProcessReq req) {
        // 1.参加活动
        PartakeRes partakeRes = activityPartake.doPartake(new PartakeReq(req.getUId(), req.getActivityId()));
        if (!ResponseCode.SUCCESS.getCode().equals(partakeRes.getCode())) {
            return new DrawProcessRes(partakeRes.getCode(), partakeRes.getInfo());
        }

        Long strategyId = partakeRes.getStrategyId();
        Long takeId = partakeRes.getTakeId();

        // 2. 进行抽奖
        DrawRes drawRes = drawExec.doDrawExec(new DrawReq(req.getUId(), strategyId, String.valueOf(takeId)));
        if (DrawState.FAIL.getCode().equals(drawRes.getDrawState())) {
            return new DrawProcessRes(ResponseCode.LOSING_DRAW.getInfo(), ResponseCode.LOSING_DRAW.getInfo());
        }
        DrawAwardVO drawAwardVO = drawRes.getDrawAwardVO();

        // 3. 结果落库, 本地消息表添加记录
        DrawOrderVO drawOrderVO = buildDrawOrderVO(req, strategyId, takeId, drawAwardVO);


        Result recordResult = activityPartake.recordDrawOrder(drawOrderVO);
        if (!ResponseCode.SUCCESS.getCode().equals(recordResult.getCode())) {
            return new DrawProcessRes(recordResult.getCode(), recordResult.getInfo());
        }

        // 4. 发送MQ, 触发发奖流程,

        InvoiceVO invoiceVO = buildInvoiceVO(drawOrderVO);

        ListenableFuture<SendResult<String, Object>> future = kafkaProducer.sendLotteryInvoice(invoiceVO);



        // 5. 异步监听发送结果
        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable ex) {
                // 4.1 MQ 消息发送失败，更新数据库表 user_strategy_export_mq.mqState = 2 【等待定时任务扫码补偿MQ消息】
                activityMQStateRepository.updateInvoiceMqState(invoiceVO.getUId(), invoiceVO.getOrderId(), MQState.FAIL.getCode());
            }

            @Override
            public void  onSuccess(SendResult<String, Object> result) {
                // 4.2 MQ 消息发送完成，更新数据库表 user_strategy_export_mq.mqState = 1，删除本地消息表记录
                activityMQStateRepository.deleteInvoiceMqState(invoiceVO.getUId(), invoiceVO.getOrderId(), MQState.COMPLETE.getCode());
            }
        });


        // 6. 返回结果
        return new DrawProcessRes(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getInfo(), drawAwardVO);
    }

    @Override
    public RuleQuantificationCrowdResult doRuleQuantificationCrowd(DecisionMatterReq req) {
        // 1.量化决策
        EngineRes engineRes = engineFilter.process(req);

        if(!engineRes.isSuccess()) {
            return new RuleQuantificationCrowdResult(ResponseCode.RULE_ERR.getCode(), ResponseCode.RULE_ERR.getInfo());
        }

        // 2.封装结果
        RuleQuantificationCrowdResult ruleQuantificationCrowdResult = new RuleQuantificationCrowdResult(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getInfo());
        ruleQuantificationCrowdResult.setActivityId(Long.valueOf(engineRes.getNodeValue()));
        return ruleQuantificationCrowdResult;
    }


    private DrawOrderVO buildDrawOrderVO(DrawProcessReq req, Long strategyId, Long takeId, DrawAwardVO drawAwardVO) {
        long orderId = idGeneratorMap.get(IdGeneratorMethod.SnowFlake).nextId();
        DrawOrderVO drawOrderVO = new DrawOrderVO();
        drawOrderVO.setUId(req.getUId());
        drawOrderVO.setTakeId(takeId);
        drawOrderVO.setActivityId(req.getActivityId());
        drawOrderVO.setOrderId(orderId);
        drawOrderVO.setStrategyId(strategyId);
        drawOrderVO.setStrategyMode(drawAwardVO.getStrategyMode());
        drawOrderVO.setGrantType(drawAwardVO.getGrantType());
        drawOrderVO.setGrantDate(drawAwardVO.getGrantDate());
        drawOrderVO.setGrantState(GrantState.INIT.getCode());
        drawOrderVO.setAwardId(drawAwardVO.getAwardId());
        drawOrderVO.setAwardType(drawAwardVO.getAwardType());
        drawOrderVO.setAwardName(drawAwardVO.getAwardName());
        drawOrderVO.setAwardContent(drawAwardVO.getAwardContent());
        return drawOrderVO;
    }

    private InvoiceVO buildInvoiceVO(DrawOrderVO drawOrderVO) {
        InvoiceVO invoiceVO = new InvoiceVO();
        invoiceVO.setUId(drawOrderVO.getUId());
        invoiceVO.setOrderId(drawOrderVO.getOrderId());
        invoiceVO.setAwardId(drawOrderVO.getAwardId());
        invoiceVO.setAwardType(drawOrderVO.getAwardType());
        invoiceVO.setAwardName(drawOrderVO.getAwardName());
        invoiceVO.setAwardContent(drawOrderVO.getAwardContent());
        invoiceVO.setShippingAddress(null);
        invoiceVO.setExtInfo(null);
        return invoiceVO;
    }


}
