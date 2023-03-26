package com.un1ink.infrastructure.dao;

import com.un1ink.infrastructure.po.Award;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

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

    /**
     * 插入奖品配置
     *
     * @param list 奖品配置
     */
    void insertList(List<Award> list);


}
