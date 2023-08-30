package com.un1ink.interfaces.assembler;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;


/**
 * @description: 对象转化配置接口
 * @author：un1ink
 * @date: 2023/4/2
 */
@MapperConfig
public interface IMapping<SOURCE, TARGET> {
    /**
     * 映射同名属性
     * @param var1 源
     * @return     结果
     */
    @Mapping(target = "createTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    TARGET sourceToTarget(SOURCE var1);

    /**
     * 映射同名属性，反向
     * @param var1 源
     * @return     结果
     */
    @InheritInverseConfiguration(name = "sourceToTarget")
    SOURCE targetToSource(TARGET var1);



}
