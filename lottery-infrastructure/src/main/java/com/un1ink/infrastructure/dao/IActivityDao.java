package com.un1ink.infrastructure.dao;

import com.un1ink.infrastructure.po.Activity;
import com.un1ink.infrastructure.po.AlterState;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author un1ink
 */
@Mapper
public interface IActivityDao {

    /**
     * 插入活动
     *
     * @param req 活动请求
     */
    void insert(Activity req);

    /**
     * 查询活动
     *
     * @param activityId 活动id
     * @return 活动详情
     */
    Activity queryActivityById(Long activityId);

    /**
     * 变更活动状态
     *
     * @param alterStateVO  [activityId、beforeState、afterState]
     * @return 更新数量
     */
    int alterState(AlterState alterStateVO);

}
