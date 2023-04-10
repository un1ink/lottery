package com.un1ink.infrastructure.dao;

import com.un1ink.domain.activity.model.vo.ActivityMQStateVO;
import com.un1ink.infrastructure.po.ActivityMQState;
import com.un1ink.middleware.db.router.annotation.DBRouter;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @description:
 * @author：un1ink
 * @date: 2023/4/3
 */

@Mapper
public interface IActivityMQStateDao {
    @DBRouter
    void insertInvoiceMqState(ActivityMQState activityMQState);

    @DBRouter
    void updateInvoiceMqState(ActivityMQState activityMQState);

    @DBRouter
    void deleteInvoiceMqState(ActivityMQState activityMQState);

    @DBRouter
    List<ActivityMQState> scanInvoiceMqState();


}
