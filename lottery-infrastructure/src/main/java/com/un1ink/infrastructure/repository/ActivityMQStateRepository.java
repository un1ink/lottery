package com.un1ink.infrastructure.repository;

import com.un1ink.common.constants.ActivityState;
import com.un1ink.common.constants.MQState;
import com.un1ink.domain.activity.model.vo.ActivityMQStateVO;
import com.un1ink.domain.activity.model.vo.InvoiceVO;
import com.un1ink.domain.activity.repository.IActivityMQStateRepository;
import com.un1ink.infrastructure.dao.IActivityMQStateDao;
import com.un1ink.infrastructure.po.ActivityMQState;
import com.un1ink.middleware.db.router.strategy.IDBRouterStrategy;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: 活动状态本地消息表仓储服务
 * @author：un1ink
 * @date: 2023/4/3
 */
@Repository
public class ActivityMQStateRepository implements IActivityMQStateRepository {

    @Resource
    IActivityMQStateDao activityMQStateDao;

    @Resource
    private IDBRouterStrategy dbRouter;


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

    @Override
    public List<ActivityMQStateVO> scanInvoiceMqState() {
        List<ActivityMQState> activityMQStateList = activityMQStateDao.scanInvoiceMqState();
        List<ActivityMQStateVO> activityMQStateVOList = new ArrayList<>();
        for(ActivityMQState activityMQState : activityMQStateList){
            ActivityMQStateVO activityMQStateVO = new ActivityMQStateVO();
            activityMQStateVO.setMqState(activityMQState.getMqState());
            activityMQStateVO.setOrderId(activityMQState.getOrderId());
            activityMQStateVO.setUId(activityMQState.getUId());
            activityMQStateVOList.add(activityMQStateVO);
        }
        return activityMQStateVOList;
    }


}
