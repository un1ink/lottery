package com.un1ink.domain.activity.repository;

import com.un1ink.domain.activity.model.vo.ActivityMQStateVO;
import com.un1ink.domain.activity.model.vo.InvoiceVO;

import java.util.List;

/**
 * @description:
 * @author：un1ink
 * @date: 2023/4/3
 */
public interface IActivityMQStateRepository {
    /**
     * 更新发货单MQ状态(消费失败)
     *
     * @param uId     用户ID
     * @param orderId 订单ID
     * @param mqState MQ 发送状态
     */
    void updateInvoiceMqState(String uId, Long orderId, Integer mqState);

    /**
     * 删除发货单MQ状态(消费成功)
     *
     * @param uId     用户ID
     * @param orderId 订单ID
     * @param mqState MQ 发送状态
     */
    void deleteInvoiceMqState(String uId, Long orderId, Integer mqState);

    /**
     * 创建发货单MQ状态
     *
     * @param uId     用户ID
     * @param orderId 订单ID
     * @param mqState MQ 发送状态
     */
    void insertInvoiceMqState(String uId, Long orderId, Integer mqState);

    /**
     * 扫描本地消息表
     */

    List<ActivityMQStateVO> scanInvoiceMqState();

}
