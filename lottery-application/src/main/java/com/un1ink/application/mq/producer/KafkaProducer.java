package com.un1ink.application.mq.producer;

import com.alibaba.fastjson.JSON;
import com.un1ink.domain.activity.model.vo.InvoiceVO;
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

    public static final String TOPIC_INVOICE= "lotteryInvoice";

    public ListenableFuture<SendResult<String, Object>> sendLotteryInvoice(InvoiceVO invoiceVO) {
        String objJson = JSON.toJSONString(invoiceVO);
        logger.info("发送MQ消息 topic：{} bizId：{} message：{}", TOPIC_INVOICE, invoiceVO.getUId(), objJson);
        return kafkaTemplate.send(TOPIC_INVOICE, objJson);
    }
}
