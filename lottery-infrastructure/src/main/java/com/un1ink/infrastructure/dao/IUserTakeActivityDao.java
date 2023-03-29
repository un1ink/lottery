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
}
