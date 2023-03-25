package com.un1ink.domain.service.draw.impl;

import com.un1ink.domain.model.aggregates.StrategyRich;
import com.un1ink.domain.model.req.DrawReq;
import com.un1ink.domain.model.res.DrawRes;
import com.un1ink.domain.model.vo.AwardRateInfo;
import com.un1ink.domain.repository.IStrategyRepository;
import com.un1ink.domain.service.algorithm.IDrawAlgorithm;
import com.un1ink.domain.service.draw.DrawBase;
import com.un1ink.domain.service.draw.IDrawExec;
import com.un1ink.infrastructure.dao.IStrategyDetailDao;
import com.un1ink.infrastructure.po.Award;
import com.un1ink.infrastructure.po.Strategy;
import com.un1ink.infrastructure.po.StrategyDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class DrawExecImpl extends DrawBase implements IDrawExec {

    private Logger logger = LoggerFactory.getLogger(DrawExecImpl.class);

    @Resource
    private IStrategyRepository strategyRepository;

    @Override
    public DrawRes doDrawExec(DrawReq req) {
        logger.info("执行策略抽奖开始，strategyId：{}", req.getStrategyId());

        // 获取抽奖策略配置数据
        StrategyRich strategyRich = strategyRepository.queryStrategyRich(req.getStrategyId());
        Strategy strategy = strategyRich.getStrategy();

        List<StrategyDetail> strategyDetailList  = strategyRich.getStrategyDetailList();

        checkAndInitRateData(req.getStrategyId(), strategy.getStrategyMode(), strategyDetailList);


        IDrawAlgorithm drawAlgorithm = drawAlgorithmMap.get(strategy.getStrategyMode());
        String awardId = drawAlgorithm.randomDraw(req.getStrategyId(), new ArrayList<>());
        // 获取奖品信息

        if(null == awardId) {
            Award award = strategyRepository.queryAwardInfo(awardId);
            logger.info("未中奖！");
            return null;
        } else {
            Award award = strategyRepository.queryAwardInfo(awardId);
            logger.info("执行策略抽奖完成，中奖用户：{} 奖品ID：{} 奖品名称：{}", req.getUId(), awardId, award.getAwardName());
            return new DrawRes(req.getUId(), req.getStrategyId(), awardId, award.getAwardName());

        }


        // 封装结果



    }


}
