package com.un1ink.domain.activity.service.deploy.impl;

import com.alibaba.fastjson.JSON;
import com.un1ink.domain.activity.model.aggregates.ActivityConfigRich;
import com.un1ink.domain.activity.model.req.ActivityConfigReq;
import com.un1ink.domain.activity.model.vo.ActivityVO;
import com.un1ink.domain.activity.model.vo.AwardVO;
import com.un1ink.domain.activity.model.vo.StrategyDetailVO;
import com.un1ink.domain.activity.model.vo.StrategyVO;
import com.un1ink.domain.activity.repository.IActivityRepository;
import com.un1ink.domain.activity.service.deploy.IActivityDeploy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description: 部署活动配置服务实现
 * @author：un1ink
 * @date: 2023/3/26
 */
@Component
public class ActivityDeployImpl implements IActivityDeploy {

    private Logger logger = LoggerFactory.getLogger(ActivityDeployImpl.class);

    @Resource
    private IActivityRepository activityRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void createActivity(ActivityConfigReq req) {
        logger.info("创建活动配置开始，activityId：{}", req.getActivityId());
        ActivityConfigRich activityConfigRich = req.getActivityConfigRich();
        try {
            // 添加活动配置信息
            ActivityVO activityVO = activityConfigRich.getActivity();
            activityRepository.addActivity(activityVO);

            // 添加奖品配置信息
            List<AwardVO> awardVOList = activityConfigRich.getAwardList();
            activityRepository.addAward(awardVOList);

            // 添加策略配置
            StrategyVO strategyVO = activityConfigRich.getStrategy();
            activityRepository.addStrategy(strategyVO);

            // 添加策略明细配置
            List<StrategyDetailVO> strategyDetailVOList = strategyVO.getStrategyDetailList();
            activityRepository.addStrategyDetailList(strategyDetailVOList);

            logger.info("创建活动配置完成，activityId：{}", req.getActivityId());

        } catch (DuplicateKeyException e) {
            logger.error("创建活动配置失败，唯一索引冲突 activityId：{} reqJson：{}", req.getActivityId(), JSON.toJSONString(req), e);
            throw e;
        }

    }

    @Override
    public void updateActivity(ActivityConfigReq req) {
        /**
         * TODO: 更新活动，后续补充
         */
    }

    @Override
    public List<ActivityVO> scanToDoActivityList(Long id, Integer state) {
        return activityRepository.scanToDoActivityList(id, state);
    }
}
