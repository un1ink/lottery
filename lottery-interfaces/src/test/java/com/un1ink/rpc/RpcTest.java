package com.un1ink.rpc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @description:
 * @author：un1ink
 * @date: 2023/8/28
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RpcTest {

    @Resource
    private ILotteryActivityBooth lotteryActivityBooth;

    @Test
    public void testRpc() {
        int hashCode = lotteryActivityBooth.testRpc("testRpc");
        System.out.println("testRpc function result: " + hashCode);
    }
}
