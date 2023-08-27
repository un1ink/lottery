package com.un1ink.application.mq.cosumer;

import cn.hutool.core.lang.Assert;
import com.alibaba.fastjson.JSON;
import com.un1ink.application.mq.producer.KafkaProducer;
import com.un1ink.common.constants.AwardState;
import com.un1ink.domain.activity.model.vo.InvoiceVO;
import com.un1ink.domain.award.model.req.GoodsReq;
import com.un1ink.domain.award.model.res.DistributionRes;
import com.un1ink.domain.award.service.factory.DistributionGoodsFactory;
import com.un1ink.domain.award.service.goods.IDistributionGoods;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @description: 中奖发货单监听器
 * @author：un1ink
 * @date: 2023/4/2
 */
@Component
public class LotteryInvoiceListener {

    private final Logger logger = LoggerFactory.getLogger(LotteryInvoiceListener.class);

    @Resource
    private DistributionGoodsFactory distributionGoodsFactory;

    @KafkaListener(topics = KafkaProducer.TOPIC_INVOICE, groupId = "lottery")
    public void onMessage(ConsumerRecord<?, ?> record, Acknowledgment acknowledgment, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        Optional<?> message = Optional.ofNullable(record.value());
        if (!message.isPresent()) {
            return;
        }
        try{
            // 1. 转化对象
            InvoiceVO invoiceVO = JSON.parseObject((String) message.get(), InvoiceVO.class);
            logger.info("消费者获取对象：{}", message.get());
            // 2. 获取发送奖品工厂，执行发奖
            IDistributionGoods distributionGoodsService = distributionGoodsFactory.getDistributionGoodsService(invoiceVO.getAwardType());
            GoodsReq goodsReq = new GoodsReq();
            goodsReq.setUId(invoiceVO.getUId());
            goodsReq.setAwardId(invoiceVO.getAwardId());
            goodsReq.setOrderId(invoiceVO.getOrderId());
            goodsReq.setAwardName(invoiceVO.getAwardName());
            goodsReq.setAwardContent(invoiceVO.getAwardContent());
            goodsReq.setShippingAddress(invoiceVO.getShippingAddress());
            goodsReq.setExtInfo(invoiceVO.getExtInfo());
            DistributionRes distributionRes = distributionGoodsService.doDistribution(goodsReq);
            // 3. 打印日志
            logger.info("消费MQ消息，完成 topic：{} bizId：{} 发奖结果：{}", topic, invoiceVO.getUId(), JSON.toJSONString(distributionRes));
            // 4. 消息消费完成
            acknowledgment.acknowledge();
        } catch (Exception e) {
            // 发奖环节失败，消息重试。所有到环节，发货、更新库，都需要保证幂等。
            logger.error("消费MQ消息，失败 topic：{} message：{}", topic, message.get());
            throw e;
        }
    }

}
