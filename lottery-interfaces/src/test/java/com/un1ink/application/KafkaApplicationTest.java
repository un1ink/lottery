package com.un1ink.application;

import com.un1ink.application.mq.KafkaProducer;
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
 * @date: 2023/4/2
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class KafkaApplicationTest {

    private Logger logger = LoggerFactory.getLogger(KafkaApplicationTest.class);

    @Resource
    private KafkaProducer kafkaProducer;

    @Test
    public void test_send() throws InterruptedException {
        // 循环发送消息
        while (true) {
            kafkaProducer.send("你好，我是Lottery");
            Thread.sleep(5000);
        }
    }
}
