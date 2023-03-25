package com.un1ink.domain.service.draw;

import com.un1ink.domain.model.req.DrawReq;
import com.un1ink.domain.model.res.DrawRes;

public interface IDrawExec {
    DrawRes doDrawExec(DrawReq res);
}
