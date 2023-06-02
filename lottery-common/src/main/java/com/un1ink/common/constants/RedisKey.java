package com.un1ink.common.constants;

import groovy.transform.Canonical;

/**
 * @description:
 * @author：un1ink
 * @date: 2023/4/11
 */

public class RedisKey {
    // 抽奖活动库存 Key
    private static final String LOTTERY_ACTIVITY_STOCK_COUNT = "lottery_activity_stock_count_";

    public static String KEY_LOTTERY_ACTIVITY_STOCK_COUNT(Long activityId) {
        return LOTTERY_ACTIVITY_STOCK_COUNT + activityId;
    }

    // 抽奖活动库存锁 Key
    private static final String LOTTERY_ACTIVITY_STOCK_COUNT_TOKEN = "lottery_activity_stock_count_token_";

    public static String KEY_LOTTERY_ACTIVITY_STOCK_COUNT_TOKEN(Long activityId, Integer stockUsedCount) {
        return LOTTERY_ACTIVITY_STOCK_COUNT_TOKEN + activityId + "_" + stockUsedCount;
    }
}
