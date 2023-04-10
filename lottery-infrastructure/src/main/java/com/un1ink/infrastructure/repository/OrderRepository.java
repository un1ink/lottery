package com.un1ink.infrastructure.repository;

import com.un1ink.domain.award.repository.IOrderRepository;
import com.un1ink.infrastructure.dao.IUserStrategyExportDao;
import com.un1ink.infrastructure.po.UserStrategyExport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @description:
 * @author：un1ink
 * @date: 2023/4/1
 */

@Repository
public class OrderRepository implements IOrderRepository {

    @Resource
    private IUserStrategyExportDao userStrategyExportDao;

    @Override
    public void updateUserAwardState(String uId, Long orderId, String awardId, Integer grantState) {
        UserStrategyExport userStrategyExport = new UserStrategyExport();
        userStrategyExport.setUId(uId);
        userStrategyExport.setOrderId(orderId);
        userStrategyExport.setAwardId(awardId);
        userStrategyExport.setGrantState(grantState);
        userStrategyExportDao.updateUserAwardState(userStrategyExport);
    }



}
