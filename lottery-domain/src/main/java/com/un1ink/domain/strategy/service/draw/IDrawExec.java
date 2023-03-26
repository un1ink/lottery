package com.un1ink.domain.strategy.service.draw;

import com.un1ink.domain.strategy.model.req.DrawReq;
import com.un1ink.domain.strategy.model.res.DrawRes;

/**
 * @author un1ink
 */
public interface IDrawExec {
    DrawRes doDrawExec(DrawReq req);
}
