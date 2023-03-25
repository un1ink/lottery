package com.un1ink.domain.service.draw;

import com.un1ink.domain.model.req.DrawReq;
import com.un1ink.domain.model.res.DrawRes;

/**
 * @author un1ink
 */
public interface IDrawExec {
    DrawRes doDrawExec(DrawReq req);
}
