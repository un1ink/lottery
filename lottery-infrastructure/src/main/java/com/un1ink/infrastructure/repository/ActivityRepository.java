package com.un1ink.infrastructure.repository;

import com.un1ink.common.constants.ActivityState;
import com.un1ink.common.constants.RedisKey;
import com.un1ink.domain.activity.model.req.PartakeReq;
import com.un1ink.domain.activity.model.res.StockRes;
import com.un1ink.domain.activity.repository.IActivityRepository;
import com.un1ink.domain.activity.model.vo.*;
import com.un1ink.common.constants.ResponseCode;
import com.un1ink.infrastructure.dao.*;
import com.un1ink.infrastructure.po.*;
import com.un1ink.infrastructure.util.RedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author：un1ink
 * @date: 2023/3/26
 */
@Repository
public class ActivityRepository implements IActivityRepository {
    @Resource
    private IActivityDao activityDao;
    @Resource
    private IAwardDao awardDao;
    @Resource
    private IStrategyDao strategyDao;
    @Resource
    private IStrategyDetailDao strategyDetailDao;

    @Resource
    private IUserTakeActivityCountDao userTakeActivityCountDao;

    @Resource
    private RedisUtils redisUtils;

    private final Logger logger = LoggerFactory.getLogger(ActivityRepository.class);

    @Override
    public void addActivity(ActivityVO activity) {
        Activity req = new Activity();
        BeanUtils.copyProperties(activity, req);
        activityDao.insert(req);
    }

    @Override
    public ActivityVO queryActivityById(Long activityId) {
        Activity activity = activityDao.queryActivityById(activityId);
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
        AlterStateVO alterStateVO = new AlterStateVO(activityId, ((ActivityState) beforeState).getCode(), ((ActivityState) afterState).getCode());
        AlterState alterState = new AlterState();
        BeanUtils.copyProperties(alterStateVO, alterState);
        int count = activityDao.alterState(alterState);
        return 1 == count;
    }

    @Override
    public ActivityBillVO queryActivityBill(PartakeReq req) {
        // 查询活动信息
        Activity activity = activityDao.queryActivityById(req.getActivityId());

        // 查询用户可参加次数
        UserTakeActivityCount userTakeActivityCountReq = new UserTakeActivityCount();
        userTakeActivityCountReq.setUId(req.getUId());
        userTakeActivityCountReq.setActivityId(req.getActivityId());


        // 封装结果信息
        UserTakeActivityCount userTakeActivityCount = userTakeActivityCountDao.queryUserTakeActivityCount(userTakeActivityCountReq);
        ActivityBillVO activityBillVO = new ActivityBillVO();
        activityBillVO.setUId(req.getUId());
        activityBillVO.setActivityId(req.getActivityId());
        activityBillVO.setActivityName(activity.getActivityName());
        activityBillVO.setBeginDateTime(activity.getBeginDateTime());
        activityBillVO.setEndDateTime(activity.getEndDateTime());
        activityBillVO.setTakeCount(activity.getTakeCount());
        activityBillVO.setStockSurplusCount(activity.getStockSurplusCount());
        activityBillVO.setStrategyId(activity.getStrategyId());
        activityBillVO.setState(activity.getState());
        activityBillVO.setStockCount(activity.getStockCount());
//        System.out.println("arepositoty"+req.getActivityId()+","+activity.getStockCount());
        // System.out.println("userTakeActivityCount:" + userTakeActivityCount);
        activityBillVO.setUserTakeLeftCount(null == userTakeActivityCount ? null : userTakeActivityCount.getLeftCount());

        return activityBillVO;
    }

    @Override
    public int subtractionActivityStock(Long activityId) {
        return activityDao.subtractionActivityStock(activityId);
    }

    @Override
    public List<ActivityVO> scanToDoActivityList(Long id, Integer activityState) {

        List<Activity> activityList;

        if (ActivityState.DOING.getCode().equals(activityState)) {
            // scan doing
            activityList = activityDao.scanDoingActivityList(id);
        } else {
            // scan pass
            activityList = activityDao.scanPassActivityList(id);
        }
        List<ActivityVO> activityVOList = new ArrayList<>(activityList.size());
        for (Activity activity : activityList) {
            ActivityVO activityVO = new ActivityVO();
            activityVO.setId(activity.getId());
            activityVO.setActivityId(activity.getActivityId());
            activityVO.setActivityName(activity.getActivityName());
            activityVO.setBeginDateTime(activity.getBeginDateTime());
            activityVO.setEndDateTime(activity.getEndDateTime());
            activityVO.setState(activity.getState());
            activityVOList.add(activityVO);
        }
        return activityVOList;
    }

    @Override
    public StockRes subtractionActivityStockByRedis(String uId, Long activityId, Integer stockCount) {
        //  1. 获取抽奖活动库存 Key
        String stockKey = RedisKey.KEY_LOTTERY_ACTIVITY_STOCK_COUNT(activityId);
        System.out.println("subtractionActivityStockByRedis-stockKey:" + stockKey);


        // 2. 扣减库存，目前占用库存数
        Integer stockUsedCount = (int) redisUtils.incr(stockKey, 1);

        // 3. 超出库存判断，进行恢复原始库存
        if (stockUsedCount > stockCount) {
            redisUtils.decr(stockKey, 1);
            logger.info("超出库存判断，进行恢复原始库存。stockUsedCount:{}，stockCount:{}", stockUsedCount, stockCount);
            return new StockRes(ResponseCode.OUT_OF_STOCK.getCode(), ResponseCode.OUT_OF_STOCK.getInfo());
        }

        // 4. 以活动库存占用编号，生成对应加锁Key，细化锁的颗粒度
        String stockTokenKey = RedisKey.KEY_LOTTERY_ACTIVITY_STOCK_COUNT_TOKEN(activityId, stockUsedCount);

        // 5. 使用 Redis.setNx 加一个分布式锁
        boolean lockToken = redisUtils.setNx(stockTokenKey, 350L);
        if (!lockToken) {
            logger.info("抽奖活动{}用户秒杀{}扣减库存，分布式锁失败：{}", activityId, uId, stockTokenKey);
            return new StockRes(ResponseCode.ERR_TOKEN.getCode(), ResponseCode.ERR_TOKEN.getInfo());
        }

        return new StockRes(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getInfo(), stockTokenKey, stockCount - stockUsedCount);
    }

    @Override
    public void recoverActivityCacheStockByRedis(Long activityId, String tokenKey, String code) {
        // 删除分布式锁 Key
        redisUtils.del(tokenKey);
    }
}
