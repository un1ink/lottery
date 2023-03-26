package com.un1ink.domain.award.service.factory;

import com.un1ink.domain.award.service.goods.IDistributionGoods;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author：un1ink
 * @date: 2023/3/26
 */
@Service
public class DistributionGoodsFactory extends GoodsConfig{

    public IDistributionGoods getDistributionGoodsService(Integer awardType) {
        return goodsMap.get(awardType);
    }

}
