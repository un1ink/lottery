package com.un1ink.domain.activity.service.partake;

import com.un1ink.common.Result;
import com.un1ink.domain.activity.model.req.PartakeReq;
import com.un1ink.domain.activity.model.res.PartakeRes;
import com.un1ink.domain.activity.model.vo.ActivityPartakeRecordVO;
import com.un1ink.domain.activity.model.vo.DrawOrderVO;
import com.un1ink.domain.activity.model.vo.InvoiceVO;

import javax.websocket.SendResult;
import java.util.List;

/**
 * @description: 抽奖活动参与接口
 * @author：un1ink
 * @date: 2023/3/26
 */
public interface IActivityPartake {
    /**
     * 参与活动
     *
     * @param req 参加活动请求
     * @return 活动结果
     */
    PartakeRes doPartake(PartakeReq req);

    /**
     * 保存奖品单
     *
     * @param drawOrder 奖品单
     * @return 保存结果
     */
    Result recordDrawOrder(DrawOrderVO drawOrder);

    /**
     * 扫描发货单 MQ 状态，把未发送 MQ 的单子扫描出来，做补偿
     *
     * @param dbCount 指定分库
     * @return 发货单
     */
    List<InvoiceVO> scanInvoiceMqState(int dbCount);

    /**
     * 更新活动库存
     *
     * @param activityPartakeRecordVO   活动领取记录
     */
    void updateActivityStock(ActivityPartakeRecordVO activityPartakeRecordVO);


}
