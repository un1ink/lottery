package com.un1ink.infrastructure.dao;

import com.un1ink.infrastructure.po.UserTakeActivityCount;
import com.un1ink.middleware.db.router.annotation.DBRouter;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description:
 * @author：un1ink
 * @date: 2023/3/29
 */
@Mapper
public interface IUserTakeActivityCountDao {
    /**
     * @param userTakeActivityCountReq  查询请求(activityId, uId)
     * @return 用户可参加活动详情
     */
    @DBRouter
    UserTakeActivityCount queryUserTakeActivityCount(UserTakeActivityCount userTakeActivityCountReq);

    /**
     * @param userTakeActivityCount 第一次抽奖，插入数据
     */
    void insert(UserTakeActivityCount userTakeActivityCount);

    /**
     * @param userTakeActivityCount 用户信息(uId, activityId)
     * @return 更新数量
     */
    int updateLeftCount(UserTakeActivityCount userTakeActivityCount);
}
