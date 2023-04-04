package com.un1ink.domain.activity.repository;

import com.un1ink.common.constants.ActivityState;
import com.un1ink.domain.activity.model.req.PartakeReq;
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


}
