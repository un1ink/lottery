package com.un1ink.infrastructure.dao;


import com.un1ink.infrastructure.po.Activity;
import com.un1ink.infrastructure.po.AlterState;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author un1ink
 */
@Mapper
public interface IActivityDao {

    /**
     * 插入活动
     *
     * @param req 待插入活动对象
     */
    void insert(Activity req);

    /**
     * 查询活动
     *
     * @param activityId 活动id
     * @return 活动详情
     */
    Activity queryActivityById(Long activityId);

    /**
     * 变更活动状态
     *
     * @param alterState  [活动id、变更前状态、变更后状态]
     * @return 更新数量
     */
    int alterState(AlterState alterState);

    /**
     * 扣减活动库存
     * @param activityId   活动ID
     * @return 数据库更新条数
     */
    int subtractionActivityStock(Long activityId);

    /**
     * 扫描PASS活动
     * @param id   活动自增ID
     * @return 活动列表
     */
    List<Activity> scanPassActivityList(Long id);
    /**
     * 扫描DOING活动
     * @param id   活动自增ID
     * @return 活动列表
     */
    List<Activity> scanDoingActivityList(Long id);


    /**
     * 更新用户参加活动后，活动库存
     *
     * @param activity  入参
     */
    void updateActivityStock(Activity activity);




}
