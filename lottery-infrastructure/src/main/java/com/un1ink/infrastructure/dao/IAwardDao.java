package com.un1ink.infrastructure.dao;

import com.un1ink.infrastructure.po.Award;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author un1ink
 */
@Mapper
public interface IAwardDao {
    /**
     * 查询奖品详情
     *
     * @param awardId 奖品id
     * @return 奖品详情
     */
    Award queryAwardInfo(String awardId);
}
