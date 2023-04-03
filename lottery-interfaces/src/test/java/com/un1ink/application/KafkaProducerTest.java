package com.un1ink.application;

import com.alibaba.fastjson.JSON;
import com.un1ink.application.mq.producer.KafkaProducer;
import com.un1ink.application.process.req.DrawProcessReq;
import com.un1ink.application.process.res.DrawProcessRes;
import com.un1ink.common.constants.AwardType;
import com.un1ink.domain.activity.model.vo.InvoiceVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
/**
 * @description:
 * @author：un1ink
 * @date: 2023/4/3
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class KafkaProducerTest {

    private Logger logger = LoggerFactory.getLogger(KafkaProducerTest.class);

    @Resource
    private KafkaProducer kafkaProducer;



    @Test
    public void test_send() throws InterruptedException {

        InvoiceVO invoice = new InvoiceVO();
        invoice.setUId("un1ink");
        invoice.setOrderId(1444540456057864192L);
        invoice.setAwardId("3");
        invoice.setAwardType(AwardType.RedeemCodeGoods.getCode());
        invoice.setAwardName("Code");
        invoice.setAwardContent("苹果电脑");
        invoice.setShippingAddress(null);
        invoice.setExtInfo(null);

        kafkaProducer.sendLotteryInvoice(invoice);

        while (true){
            Thread.sleep(5000);
        }
    }



}
