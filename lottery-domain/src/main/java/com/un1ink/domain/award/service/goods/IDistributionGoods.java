package com.un1ink.domain.award.service.goods;

import com.un1ink.domain.award.model.req.GoodsReq;
import com.un1ink.domain.award.model.res.DistributionRes;

/**
 * @description: 抽象出配送货物接口，配送代表着发货，包括虚拟奖品和实物奖品
 * @author：un1ink
 * @date: 2023/3/26
 */
public interface IDistributionGoods {

    /**
     * 执行奖品配送
     *
     * @param req 待配送物品信息
     * @return 配送结果
     */
    DistributionRes doDistribution(GoodsReq req);

    /**
     * 获取配送奖品名称
     *
     * @return 配送结果
     */
    Integer getDistributionGoodName();
}
