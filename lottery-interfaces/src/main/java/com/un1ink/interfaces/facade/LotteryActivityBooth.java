package com.un1ink.interfaces.facade;

import com.alibaba.fastjson.JSON;
import com.un1ink.application.process.IActivityProcess;
import com.un1ink.application.process.req.DrawProcessReq;
import com.un1ink.application.process.res.DrawProcessRes;
import com.un1ink.application.process.res.RuleQuantificationCrowdResult;
import com.un1ink.common.constants.ResponseCode;
import com.un1ink.domain.rule.model.req.DecisionMatterReq;
import com.un1ink.domain.strategy.model.vo.DrawAwardVO;
import com.un1ink.interfaces.assembler.IMapping;
import com.un1ink.rpc.ILotteryActivityBooth;
import com.un1ink.rpc.dto.AwardDTO;
import com.un1ink.rpc.req.DrawReq;
import com.un1ink.rpc.req.QuantificationDrawReq;
import com.un1ink.rpc.res.DrawRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @description: 抽奖活动展台
 * @author：un1ink
 * @date: 2023/4/2
 */

@Component
public class LotteryActivityBooth implements ILotteryActivityBooth {
    private Logger logger = LoggerFactory.getLogger(LotteryActivityBooth.class);

    @Resource
    private IActivityProcess activityProcess;


    @Resource
    private IMapping<DrawAwardVO, AwardDTO> awardMapping;


    @Override
    public DrawRes doDraw(DrawReq drawReq) {
        try {
            // 1. 执行抽奖
            logger.info("抽奖，开始 uId：{} activityId：{}", drawReq.getUId(), drawReq.getActivityId());
            DrawProcessRes drawProcessRes = activityProcess.doDrawProcess(new DrawProcessReq(drawReq.getUId(), drawReq.getActivityId()));
            if (!ResponseCode.SUCCESS.getCode().equals(drawProcessRes.getCode())) {
                logger.error("抽奖，失败(抽奖过程异常) uId：{} activityId：{}", drawReq.getUId(), drawReq.getActivityId());
                return new DrawRes(drawProcessRes.getCode(), drawProcessRes.getInfo());
            }
            // 2.数据转换VO->DTO
            DrawAwardVO drawAwardVO = drawProcessRes.getDrawAwardVO();
            AwardDTO awardDTO  = awardMapping.sourceToTarget(drawAwardVO);
            awardDTO.setActivityId(drawReq.getActivityId());
            // 3.封装数据
            DrawRes drawRes = new DrawRes(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getInfo());
            drawRes.setAwardDTO(awardDTO);
            logger.info("抽奖，完成 uId：{} activityId：{} drawRes：{}", drawReq.getUId(), drawReq.getActivityId(), JSON.toJSONString(drawRes));
            return drawRes;
        } catch (Exception e) {
            logger.error("抽奖，失败 uId：{} activityId：{} reqJson：{}", drawReq.getUId(), drawReq.getActivityId(), JSON.toJSONString(drawReq), e);
            return new DrawRes(ResponseCode.UN_ERROR.getCode(), ResponseCode.UN_ERROR.getInfo());
        }
    }

    @Override
    public DrawRes doQuantificationDraw(QuantificationDrawReq quantificationDrawReq) {
        try {
            logger.info("量化人群抽奖，开始 uId：{} treeId：{}", quantificationDrawReq.getUId(), quantificationDrawReq.getTreeId());
            // 1. 执行规则引擎，获取用户可以参与的活动号
            DecisionMatterReq decisionMatterReq = new DecisionMatterReq();
            decisionMatterReq.setTreeId(quantificationDrawReq.getTreeId());
            decisionMatterReq.setUserId(quantificationDrawReq.getUId());
            decisionMatterReq.setValMap(quantificationDrawReq.getValMap());
            RuleQuantificationCrowdResult ruleQuantificationCrowdResult = activityProcess.doRuleQuantificationCrowd(decisionMatterReq);

            if (!ResponseCode.SUCCESS.getCode().equals(ruleQuantificationCrowdResult.getCode())) {
                logger.error("量化人群抽奖，失败(规则引擎执行异常) uId：{} treeId：{}", quantificationDrawReq.getUId(), quantificationDrawReq.getTreeId());
                return new DrawRes(ruleQuantificationCrowdResult.getCode(), ruleQuantificationCrowdResult.getInfo());
            }

            // 2. 执行抽奖
            Long activityId = ruleQuantificationCrowdResult.getActivityId();
            DrawProcessRes drawProcessRes = activityProcess.doDrawProcess(new DrawProcessReq(quantificationDrawReq.getUId(), activityId));
            if (!ResponseCode.SUCCESS.getCode().equals(drawProcessRes.getCode())) {
                logger.error("量化人群抽奖，失败(抽奖过程异常) uId：{} treeId：{}", quantificationDrawReq.getUId(), quantificationDrawReq.getTreeId());
                return new DrawRes(drawProcessRes.getCode(), drawProcessRes.getInfo());
            }

            // 3. 数据转换
            DrawAwardVO drawAwardVO = drawProcessRes.getDrawAwardVO();
            AwardDTO awardDTO = awardMapping.sourceToTarget(drawAwardVO);
            awardDTO.setActivityId(activityId);

            // 4. 数据封装
            DrawRes drawRes = new DrawRes(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getInfo());
            drawRes.setAwardDTO(awardDTO);

            logger.info("量化人群抽奖，完成 uId：{} treeId：{} drawRes：{}", quantificationDrawReq.getUId(), quantificationDrawReq.getTreeId(), JSON.toJSONString(drawRes));
            return drawRes;

        } catch (Exception e) {
            logger.error("量化人群抽奖，失败 uId：{} treeId：{} reqJson：{}", quantificationDrawReq.getUId(), quantificationDrawReq.getTreeId(), JSON.toJSONString(quantificationDrawReq), e);
            return new DrawRes(ResponseCode.UN_ERROR.getCode(), ResponseCode.UN_ERROR.getInfo());
        }
    }
}
