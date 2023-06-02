package com.un1ink.domain.activity.repository;

import com.un1ink.common.constants.ActivityState;
import com.un1ink.domain.activity.model.req.PartakeReq;
import com.un1ink.domain.activity.model.res.StockRes;
import com.un1ink.domain.activity.model.vo.*;

import java.util.List;

/**
 * @description:
 * @author：un1ink
 * @date: 2023/3/26
 */
public interface IActivityRepository {

    /**
     * 添加活动配置
     * @param activity 活动配置
     */
    void addActivity(ActivityVO activity);

    ActivityVO queryActivityById(Long activityId);
    /**
     * 添加奖品配置集合
     *
     * @param awardList 奖品配置集合
     */
    void addAward(List<AwardVO> awardList);

    /**
     * 添加策略配置
     *
     * @param strategy 策略配置
     */
    void addStrategy(StrategyVO strategy);

    /**
     * 添加策略明细配置
     *
     * @param strategyDetailList 策略明细集合
     */
    void addStrategyDetailList(List<StrategyDetailVO> strategyDetailList);

    /**
     * 变更活动状态
     *
     * @param activityId    活动ID
     * @param beforeState   修改前状态
     * @param afterState    修改后状态
     * @return              更新结果
     */
    boolean alterStatus(Long activityId, Enum<ActivityState> beforeState, Enum<ActivityState> afterState);
    /**
     * 查询活动详单
     *
     * @param req 参与活动请求
     * @return 账单活动
     */
    ActivityBillVO queryActivityBill(PartakeReq req);

    /**
     * 扣减活动库存
     * @param activityId   活动ID
     * @return 结果
     */
    int subtractionActivityStock(Long activityId);

    /**
     * 扫描活动id
     * @param id 活动的自增id, 非activityId
     * @return 活动列表
     */
    List<ActivityVO> scanToDoActivityList(Long id, Integer activityState);

    /**
     * 扣减活动库存，通过Redis
     *
     * @param uId        用户ID
     * @param activityId 活动ID
     * @param stockCount 总库存
     * @return 扣减结果
     */
    StockRes subtractionActivityStockByRedis(String uId, Long activityId, Integer stockCount);

    /**
     * 恢复活动库存，通过Redis 【如果非常异常，则需要进行缓存库存恢复，只保证不超卖的特性，所以不保证一定能恢复占用库存，另外最终可以由任务进行补偿库存】
     *
     * @param activityId    活动ID
     * @param tokenKey      分布式 KEY 用于清理
     * @param code          状态
     */
    void recoverActivityCacheStockByRedis(Long activityId, String tokenKey, String code);



}
