package com.un1ink.application.process.impl;

import com.un1ink.application.process.IActivityProcess;
import com.un1ink.application.process.req.DrawProcessReq;
import com.un1ink.application.process.res.DrawProcessRes;
import com.un1ink.common.constants.DrawState;
import com.un1ink.common.constants.GrantState;
import com.un1ink.common.constants.IdGeneratorMethod;
import com.un1ink.common.constants.ResponseCode;
import com.un1ink.domain.activity.model.req.PartakeReq;
import com.un1ink.domain.activity.model.res.PartakeRes;
import com.un1ink.domain.activity.model.vo.DrawOrderVO;
import com.un1ink.domain.activity.service.partake.IActivityPartake;
import com.un1ink.domain.strategy.model.req.DrawReq;
import com.un1ink.domain.strategy.model.res.DrawRes;
import com.un1ink.domain.strategy.model.vo.DrawAwardInfo;
import com.un1ink.domain.strategy.service.draw.IDrawExec;
import com.un1ink.domain.support.ids.IIdGenerator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @description:
 * @author：un1ink
 * @date: 2023/3/30
 */

@Service
public class ActivityProcessImpl implements IActivityProcess {

    @Resource
    private IActivityPartake activityPartake;

    @Resource
    private IDrawExec drawExec;

    @Resource
    private Map<IdGeneratorMethod, IIdGenerator> idGeneratorMap;


    @Override
    public DrawProcessRes doDrawProcess(DrawProcessReq req) {
        // 1.参加活动
        PartakeRes partakeRes = activityPartake.doPartake(new PartakeReq(req.getUId(), req.getActivityId()));
        if (!ResponseCode.SUCCESS.getCode().equals(partakeRes.getCode())) {
            return new DrawProcessRes(partakeRes.getCode(), partakeRes.getInfo());
        }

        Long strategyId = partakeRes.getStrategyId();
        Long takeId = partakeRes.getTakeId();

        // 2. 进行抽奖
        DrawRes drawRes = drawExec.doDrawExec(new DrawReq(req.getUId(), strategyId, String.valueOf(takeId)));
        if (DrawState.FAIL.getCode().equals(drawRes.getDrawState())) {
            return new DrawProcessRes(ResponseCode.LOSING_DRAW.getInfo(), ResponseCode.LOSING_DRAW.getInfo());
        }
        DrawAwardInfo drawAwardInfo = drawRes.getDrawAwardInfo();

        // 3. 结果落库
        activityPartake.recordDrawOrder(buildDrawOrderVO(req, drawRes.getStrategyId(), takeId, drawAwardInfo));

        // 4. 发送MQ, 触发发奖流程

        // 5. 返回结果
        return new DrawProcessRes(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getInfo(), drawAwardInfo);
    }


    private DrawOrderVO buildDrawOrderVO(DrawProcessReq req, Long strategyId, Long takeId, DrawAwardInfo drawAwardInfo) {
        long orderId = idGeneratorMap.get(IdGeneratorMethod.SnowFlake).nextId();
        DrawOrderVO drawOrderVO = new DrawOrderVO();
        drawOrderVO.setUId(req.getUId());
        drawOrderVO.setTakeId(takeId);
        drawOrderVO.setActivityId(req.getActivityId());
        drawOrderVO.setOrderId(orderId);
        drawOrderVO.setStrategyId(strategyId);
        drawOrderVO.setStrategyMode(drawAwardInfo.getStrategyMode());
        drawOrderVO.setGrantType(drawAwardInfo.getGrantType());
        drawOrderVO.setGrantDate(drawAwardInfo.getGrantDate());
        drawOrderVO.setGrantState(GrantState.INIT.getCode());
        drawOrderVO.setAwardId(drawAwardInfo.getAwardId());
        drawOrderVO.setAwardType(drawAwardInfo.getAwardType());
        drawOrderVO.setAwardName(drawAwardInfo.getAwardName());
        drawOrderVO.setAwardContent(drawAwardInfo.getAwardContent());
        return drawOrderVO;
    }


}
