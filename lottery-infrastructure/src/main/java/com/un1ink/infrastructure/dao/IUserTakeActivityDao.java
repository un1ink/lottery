package com.un1ink.infrastructure.dao;

import com.un1ink.middleware.db.router.annotation.DBRouter;

import com.un1ink.infrastructure.po.UserTakeActivity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description:
 * @author：un1ink
 * @date: 2023/3/28
 */
@Mapper
public interface IUserTakeActivityDao {
    /**
     * 添加用户领取信息
     * @param userTakeActivity 用户参加活动信息
     */
    void insert(UserTakeActivity userTakeActivity);

    /**
     * 查询有无未使用消费卷
     * @param userTakeActivity
     * @return 未使用活动卷的对象
     */
    @DBRouter
    UserTakeActivity queryNoConsumedTakeActivityOrder(UserTakeActivity userTakeActivity);
    /**
     * 锁定活动参加记录(将未使用的活动状态0置为1)
     *
     * @param userTakeActivity 用户参加活动请求
     * @return 被锁定记录数
     */
    int lockTackActivity(UserTakeActivity userTakeActivity);

}
