package com.un1ink.domain.strategy.service.draw;

import com.un1ink.common.constants.DrawState;
import com.un1ink.common.constants.StrategyMode;
import com.un1ink.domain.strategy.model.aggregates.StrategyRich;
import com.un1ink.domain.strategy.model.req.DrawReq;
import com.un1ink.domain.strategy.model.res.DrawRes;
import com.un1ink.domain.strategy.model.vo.*;
import com.un1ink.domain.strategy.service.algorithm.IDrawAlgorithm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static com.un1ink.common.constants.StrategyMode.SINGLE;
import com.un1ink.common.constants.DrawState;

/**
 * @description:
 * @author：un1ink
 * @date: 2023/3/25
 */
public abstract class AbstractDrawBase extends DrawStrategySupport implements IDrawExec{
    private Logger logger = LoggerFactory.getLogger(AbstractDrawBase.class);


    @Override
    public DrawRes doDrawExec(DrawReq req) {
        // 1.获取抽奖测类
        StrategyRich strategyRich = super.queryStrategyRich(req.getStrategyId());
        StrategyBriefVO strategy = strategyRich.getStrategy();

        // 2.校验初始化
        this.checkAndInitRateData(req.getStrategyId(), strategy.getStrategyMode(), strategyRich.getStrategyDetailList());

        // 3.获取不在抽奖范围内的列表，包括：奖品库存为空、风控策略、临时调整等
        List<String> excludeAwardIds = this.queryExcludeAwardIds( req.getStrategyId());

        // 4.执行抽奖算法
        String awardId = this.drawAlgorithm(req.getStrategyId(), drawAlgorithmMap.get(strategy.getStrategyMode()), excludeAwardIds);
        // 5.包装抽奖结果
        return buildDrawResult(req.getUId(), req.getStrategyId(), awardId, strategy);


    }
    /**
     * 查询不在抽奖范围内的奖品
     *
     * @param strategyId    策略id
     * @return 非奖品列表
     */
    protected abstract List<String> queryExcludeAwardIds(Long strategyId);

    /**
     * 执行抽奖算法
     *
     * @param strategyId 策略id
     * @param drawAlgorithm 抽奖算法
     * @return 奖品id
     */
    protected abstract String drawAlgorithm(Long strategyId, IDrawAlgorithm drawAlgorithm, List<String> excludeAwardIds);

    public void checkAndInitRateData(Long strategyId, Integer strategyMode, List<StrategyDetailBriefVO> strategyDetailList) {
        // 非单项概率不需要初始化数组
        if (!StrategyMode.SINGLE.getCode().equals(strategyMode)) {
            return;
        }
        IDrawAlgorithm drawAlgorithm = drawAlgorithmMap.get(strategyMode);
        // 已被初始化则直接返回
        if (drawAlgorithm.isExistRateTuple(strategyId)) {
            return;
        }

        List<AwardRateInfo> awardRateInfoList = new ArrayList<>(strategyDetailList.size());
        for (StrategyDetailBriefVO strategyDetail : strategyDetailList) {
            awardRateInfoList.add(new AwardRateInfo(strategyDetail.getAwardId(), strategyDetail.getAwardRate()));
        }
        drawAlgorithm.initRateTuple(strategyId, awardRateInfoList);
    }

    private DrawRes buildDrawResult(String uId, Long strategyId, String awardId, StrategyBriefVO strategy) {
        if(null == awardId) {
            logger.info("执行策略抽奖完成【未中奖】，用户：{} 策略ID：{}", uId, strategyId);
            return new DrawRes(uId, strategyId, DrawState.FAIL.getCode(), null);
        }

        AwardBriefVO award = super.queryAwardInfoByAwardId(awardId);
        DrawAwardInfo drawAwardInfo = new DrawAwardInfo();
        drawAwardInfo.setAwardId(award.getAwardId());
        drawAwardInfo.setAwardName(award.getAwardName());
        drawAwardInfo.setAwardType(award.getAwardType());
        drawAwardInfo.setAwardContent(award.getAwardContent());
        drawAwardInfo.setStrategyMode(strategy.getStrategyMode());
        drawAwardInfo.setGrantType(strategy.getGrantType());
        drawAwardInfo.setGrantDate(strategy.getGrantDate());

        logger.info("执行策略抽奖完成【已中奖】，用户：{} 策略ID：{} 奖品ID：{} 奖品名称：{}", uId, strategyId, awardId, award.getAwardName());
        return new DrawRes(uId, strategyId, DrawState.SUCCESS.getCode(), drawAwardInfo);


    }
}
