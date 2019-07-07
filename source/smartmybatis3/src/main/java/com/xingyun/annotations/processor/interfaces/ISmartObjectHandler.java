package com.xingyun.annotations.processor.interfaces;

import java.util.List;

public interface ISmartObjectHandler {

    /**
     * 获取注解映射的数据库表列名
     * @param mClass
     * @return
     */
    List<String> getDBColumnList(Class<?> mClass);
    /**
     * 获取字段名称列表
     * @param mClass
     * @return
     */
    List<String> getFieldNameList(Class<?> mClass);
    /**
     * 获取字段类型带包名列表
     * @param mClass
     * @return
     */
    List<String> getFieldTypeList(Class<?> mClass);
    /**
     * 获取字段类型不带包名列表
     * @param mClass
     * @return
     */
    List<String> getFieldSimpleTypeList(Class<?> mClass);
}
