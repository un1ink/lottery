package com.un1ink.domain.activity.model.res;

import com.un1ink.common.Result;
import lombok.Getter;
import lombok.Setter;

/**
 * @description:
 * @author：un1ink
 * @date: 2023/4/10
 */
@Setter
@Getter
public class StockRes extends Result {
    /**
     * 库存 Key
     */
    private String stockKey;
    /**
     * activity 库存剩余
     */
    private Integer stockSurplusCount;

    public StockRes(String code, String info) {
        super(code, info);
    }

    public StockRes(String code, String info, String stockKey, Integer stockSurplusCount) {
        super(code, info);
        this.stockKey = stockKey;
        this.stockSurplusCount = stockSurplusCount;

    }
}
