package com.un1ink.application.mq.producer;

import com.alibaba.fastjson.JSON;
import com.un1ink.common.constants.MQState;
import com.un1ink.domain.activity.model.vo.ActivityPartakeRecordVO;
import com.un1ink.domain.activity.model.vo.InvoiceVO;
import com.un1ink.domain.activity.repository.IActivityMQStateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import javax.annotation.Resource;

/**
 * @description: 消息生产者者
 * @author：un1ink
 * @date: 2023/4/2
 */
@Component
public class KafkaProducer {

    private final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

    @Resource
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Resource
    IActivityMQStateRepository activityMQStateRepository;

    public static final String TOPIC_INVOICE= "lottery_invoice";
    public static final String TOPIC_ACTIVITY_PARTAKE= "lottery_activity_partake";

    /**
     * mq更新发货信息，采用本地消息表保证可靠性
     * */
    public ListenableFuture<SendResult<String, Object>> sendLotteryInvoice(InvoiceVO invoiceVO) {
        String objJson = JSON.toJSONString(invoiceVO);
        logger.info("发送MQ消息 topic：{} bizId：{} message：{}", TOPIC_INVOICE, invoiceVO.getUId(), objJson);
        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(TOPIC_INVOICE, objJson);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable ex) {
                // 1. MQ 消息发送失败，更新数据库表 user_strategy_export_mq.mqState = 2
                activityMQStateRepository.updateInvoiceMqState(invoiceVO.getUId(), invoiceVO.getOrderId(), MQState.FAIL.getCode());
                logger.info("异步更新数据库发货消息发送失败");
            }

            @Override
            public void  onSuccess(SendResult<String, Object> result) {
                // 2. MQ 消息发送完成，删除本地消息表记录
                activityMQStateRepository.deleteInvoiceMqState(invoiceVO.getUId(), invoiceVO.getOrderId(), MQState.COMPLETE.getCode());
                logger.info("异步更新数据库发货消息发送成功");

            }
        });
        return future;
    }

    public ListenableFuture<SendResult<String, Object>> sendLotteryActivityPartakeRecord(ActivityPartakeRecordVO activityPartakeRecordVO) {
        String objJson = JSON.toJSONString(activityPartakeRecordVO);
        logger.info("发送MQ消息(参加活动记录) topic：{} bizId：{} message：{}", TOPIC_ACTIVITY_PARTAKE, activityPartakeRecordVO.getUId(), objJson);
        ListenableFuture<SendResult<String, Object>> future =  kafkaTemplate.send(TOPIC_ACTIVITY_PARTAKE, objJson);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable ex) {
                // TODO 更新本地消息表
                logger.info("异步更新数据库库存消息发送失败");
            }

            @Override
            public void  onSuccess(SendResult<String, Object> result) {
                // TODO MQ 消息发送完成
                logger.info("异步更新数据库库存消息发送成功");
            }
        });
        return future;
    }
}
