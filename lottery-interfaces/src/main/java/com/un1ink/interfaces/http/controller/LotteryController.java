package com.un1ink.interfaces.http.controller;

import com.alibaba.fastjson.JSON;
import com.un1ink.rpc.ILotteryActivityBooth;
import com.un1ink.rpc.req.DrawReq;
import com.un1ink.rpc.res.DrawRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

/**
 * @description:
 * @author：un1ink
 * @date: 2023/8/30
 */

@RestController
public class LotteryController {

    Logger logger = LoggerFactory.getLogger(LotteryController.class);

    @Autowired
    ILotteryActivityBooth lotteryActivityBooth;


    @PostMapping("/doDraw")
    public ResponseEntity<DrawRes> processJsonData(@RequestBody DrawReq drawReq) {
        logger.info("请求入参：{}", JSON.toJSONString(drawReq));
        DrawRes drawRes = lotteryActivityBooth.doDraw(drawReq);
        return ResponseEntity.ok(drawRes);
    }
}
