package com.un1ink.infrastructure.repository;

import com.un1ink.common.constants.ActivityState;
import com.un1ink.domain.activity.repository.IActivityRepository;
import com.un1ink.domain.activity.model.vo.*;
import com.un1ink.infrastructure.dao.*;
import com.un1ink.infrastructure.po.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author：un1ink
 * @date: 2023/3/26
 */
@Component
public class ActivityRepository implements IActivityRepository {
    @Resource
    private IActivityDao activityDao;
    @Resource
    private IAwardDao awardDao;
    @Resource
    private IStrategyDao strategyDao;
    @Resource
    private IStrategyDetailDao strategyDetailDao;

    @Override
    public void addActivity(ActivityVO activity) {
        Activity req = new Activity();
        BeanUtils.copyProperties(activity, req);
        activityDao.insert(req);
    }

    @Override
    public ActivityVO queryActivityById(Long activityId) {
        Activity activity =  activityDao.queryActivityById(activityId);
        ActivityVO activityVO = new ActivityVO();
        BeanUtils.copyProperties(activity, activityVO);
        return activityVO;
    }

    @Override
    public void addAward(List<AwardVO> awardList) {
        List<Award> req = new ArrayList<>();
        for (AwardVO awardVO : awardList) {
            Award award = new Award();
            BeanUtils.copyProperties(awardVO, award);
            req.add(award);
        }
        awardDao.insertList(req);

    }

    @Override
    public void addStrategy(StrategyVO strategy) {
        Strategy req = new Strategy();
        BeanUtils.copyProperties(strategy, req);
        strategyDao.insert(req);

    }

    @Override
    public void addStrategyDetailList(List<StrategyDetailVO> strategyDetailList) {
        List<StrategyDetail> req = new ArrayList<>();
        for (StrategyDetailVO strategyDetailVO : strategyDetailList) {
            StrategyDetail strategyDetail = new StrategyDetail();
            BeanUtils.copyProperties(strategyDetailVO, strategyDetail);
            req.add(strategyDetail);
        }
        strategyDetailDao.insertList(req);

    }

    @Override
    public boolean alterStatus(Long activityId, Enum<ActivityState> beforeState, Enum<ActivityState> afterState) {
        AlterStateVO alterStateVO = new AlterStateVO(activityId,((ActivityState) beforeState).getCode(),((ActivityState) afterState).getCode());
        AlterState alterState = new AlterState();
        BeanUtils.copyProperties(alterStateVO, alterState);
        int count = activityDao.alterState(alterState);
        return 1 == count;
    }
}
