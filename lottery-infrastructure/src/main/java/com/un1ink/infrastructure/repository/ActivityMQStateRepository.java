package com.un1ink.infrastructure.repository;

import com.un1ink.common.constants.MQState;
import com.un1ink.domain.activity.repository.IActivityMQStateRepository;
import com.un1ink.infrastructure.dao.IActivityMQStateDao;
import com.un1ink.infrastructure.po.ActivityMQState;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @description:
 * @author：un1ink
 * @date: 2023/4/3
 */
@Repository
public class ActivityMQStateRepository implements IActivityMQStateRepository {

    @Resource
    IActivityMQStateDao activityMQStateDao;


    @Override
    public void updateInvoiceMqState(String uId, Long orderId, Integer mqState) {
        ActivityMQState activityMQState = new ActivityMQState(uId, orderId, mqState);
        activityMQStateDao.updateInvoiceMqState(activityMQState);

    }

    @Override
    public void deleteInvoiceMqState(String uId, Long orderId, Integer mqState) {
        ActivityMQState activityMQState = new ActivityMQState(uId, orderId, mqState);
        activityMQStateDao.deleteInvoiceMqState(activityMQState);

    }

    @Override
    public void insertInvoiceMqState(String uId, Long orderId, Integer mqState) {
        ActivityMQState activityMQState = new ActivityMQState(uId, orderId, mqState);
        activityMQStateDao.insertInvoiceMqState(activityMQState);
    }
}
