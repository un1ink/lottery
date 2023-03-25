package com.un1ink.rpc;

import com.un1ink.rpc.req.ActivityReq;
import com.un1ink.rpc.res.ActivityRes;

public interface IActivityBooth {

    ActivityRes queryActivityById(ActivityReq req);

}
