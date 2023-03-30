package com.un1ink.domain.activity.repository;

import com.un1ink.domain.activity.model.vo.DrawOrderVO;
import com.un1ink.domain.activity.model.vo.UserTakeActivityVO;

import java.util.Date;

/**
 * @description: 用户参加活动窗口
 * @author：un1ink
 * @date: 2023/3/29
 */
public interface IUserTakeActivityRepository {
    /**
     * 扣减个人活动次数
     *
     * @param activityId 活动id
     * @param activityName  活动名称
     * @param takeCount 活动总参与次数
     * @param userTakeLeftCount 活动用户剩余参与次数
     * @param uId 用户id
     * @param partakeDate 参加活动时间
     *
     * @return 更新结果
     */
    int subtractionLeftCount(Long activityId, String activityName, Integer takeCount, Integer userTakeLeftCount, String uId, Date partakeDate );

    /**
     * 参加活动
     *
     * @param activityId 活动id
     * @param activityName  活动名称
     * @param takeCount 活动总参与次数
     * @param userTakeLeftCount 活动用户剩余参与次数
     * @param uId 用户id
     * @param partakeDate 参加活动时间
     * @param takeId 参加id
     *
     */
    void takeActivity(Long activityId, String activityName, Integer takeCount, Integer userTakeLeftCount, String uId, Date partakeDate, Long takeId);


    /**
     * 锁定活动参加记录
     * @param uId 用户Id
     * @param activityId 活动Id
     * @param takeId 参加记录id
     *
     * @return 更新结果
     */
    int lockTakeActivity(String uId, Long activityId, Long takeId);

    /**
     * 保存抽奖信息
     *
     * @param drawOrderVO 中奖单
     */
    void saveUserStrategyExport(DrawOrderVO drawOrderVO);

    /**
     *
     */
    UserTakeActivityVO queryNoConsumedTakeActivityOrder(Long activityId, String uId);


}
