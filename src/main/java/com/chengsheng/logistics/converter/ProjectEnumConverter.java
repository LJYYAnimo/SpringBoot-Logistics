package com.chengsheng.logistics.converter;

import com.chengsheng.logistics.enums.ProjectEnum;

import javax.persistence.AttributeConverter;

/**
 * 项目枚举类型转换器
 * @author 刘金泳
 * @Date 2019/8/30
 */
public class ProjectEnumConverter implements AttributeConverter<ProjectEnum,Integer> {

    /**
     * 将枚举转换成数据库列
     * @param projectEnums
     * @return
     */
    @Override
    public Integer convertToDatabaseColumn(ProjectEnum projectEnums) {
        return projectEnums.getCode();
    }

    /**
     * 将数据库列转换成枚举
     * @param code
     * @return
     */
    @Override
    public ProjectEnum convertToEntityAttribute(Integer code) {
        return ProjectEnum.getByCode(code);
    }
}
