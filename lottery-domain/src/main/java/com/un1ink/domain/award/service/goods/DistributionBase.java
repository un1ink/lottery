package com.un1ink.domain.award.service.goods;

import com.un1ink.domain.award.repository.IOrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * @description:
 * @author：un1ink
 * @date: 2023/3/26
 */
public class DistributionBase {
    protected Logger logger = LoggerFactory.getLogger(DistributionBase.class);

    @Resource
    private IOrderRepository awardRepository;

    protected void updateUserAwardState(String uId, Long orderId, String awardId, Integer grantState) {
        awardRepository.updateUserAwardState(uId, orderId, awardId, grantState);
    }
}
