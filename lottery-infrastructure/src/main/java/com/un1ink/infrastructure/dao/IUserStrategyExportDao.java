package com.un1ink.infrastructure.dao;


import com.un1ink.infrastructure.po.UserStrategyExport;


import com.un1ink.middleware.db.router.annotation.DBRouter;
import com.un1ink.middleware.db.router.annotation.DBRouterStrategy;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description:
 * @author：un1ink
 * @date: 2023/3/28
 */
@Mapper
@DBRouterStrategy(splitTable = true)
public interface IUserStrategyExportDao {
    /**
     * 新增数据
     * @param userStrategyExport 用户策略
     */
    @DBRouter
    void insert(UserStrategyExport userStrategyExport);

    /**
     * 查询数据
     * @param uId 用户ID
     * @return 用户策略
     */
    @DBRouter
    UserStrategyExport queryUserStrategyExportByUId(String uId);


    /**
     * 更新发奖状态
     * @param userStrategyExport 发奖信息
     */
    @DBRouter
    void updateUserAwardState(UserStrategyExport userStrategyExport);

    /**
     * 更新发送MQ状态
     * @param userStrategyExport 发送消息
     */
    @DBRouter
    void updateInvoiceMqState(UserStrategyExport userStrategyExport);

    @DBRouter
    UserStrategyExport getUserStrategyExportByActivityMQState(UserStrategyExport userStrategyExport);


}
