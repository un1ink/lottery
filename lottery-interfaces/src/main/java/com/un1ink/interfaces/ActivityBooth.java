package com.un1ink.interfaces;

import com.un1ink.infrastructure.dao.IActivityDao;
import com.un1ink.rpc.ILotteryActivityBooth;
import com.un1ink.rpc.req.DrawReq;
import com.un1ink.rpc.req.QuantificationDrawReq;
import com.un1ink.rpc.res.DrawRes;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;

@Service
public class ActivityBooth implements ILotteryActivityBooth {

    @Resource
    private IActivityDao activityDao;


    @Override
    public DrawRes doDraw(DrawReq drawReq) {
        return null;
    }

    @Override
    public DrawRes doQuantificationDraw(QuantificationDrawReq quantificationDrawReq) {
        return null;
    }
}
