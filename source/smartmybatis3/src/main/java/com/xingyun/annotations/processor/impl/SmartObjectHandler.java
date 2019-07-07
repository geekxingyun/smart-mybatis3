package com.xingyun.annotations.processor.impl;

import com.xingyun.annotations.model.DBColumn;
import com.xingyun.annotations.processor.interfaces.ISmartObjectHandler;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class SmartObjectHandler implements ISmartObjectHandler {
    /**
     * @param mClass
     * @return 获取注解的字段映射的数据库列名
     */
    @SuppressWarnings("unused")
    @Override
    public List<String> getDBColumnList(Class<?> mClass){
        List<String> dbColumnList=new ArrayList<>();
        //遍历字段集合
        Field[] fieldArray=mClass.getDeclaredFields();
        for (Field field:fieldArray
        ) {
            DBColumn dbColumn=field.getAnnotation(DBColumn.class);
            String dbColumnName;
            if(null!=dbColumn){
                dbColumnName=dbColumn.name();
                dbColumnList.add(dbColumnName);
            }
        }
        return dbColumnList;
    }

    /**
     * 获取成员属性名称列表
     * @param mClass
     * @return
     */
    @SuppressWarnings("unused")
    @Override
    public List<String> getFieldNameList(Class<?> mClass){
        List<String> fieldNameList=new ArrayList<>();
        //遍历字段集合
        Field[] fieldArray=mClass.getDeclaredFields();
        for (Field field:fieldArray
        ) {
            fieldNameList.add(field.getName());
        }
        return fieldNameList;
    }

    /**
     * 获取成员属性类型不包括包名
     * @param mClass
     * @return
     */
    @SuppressWarnings("unused")
    @Override
    public List<String> getFieldSimpleTypeList(Class<?> mClass){
        List<String> fieldSimpleTypeList=new ArrayList<>();
        //遍历字段集合
        Field[] fieldArray=mClass.getDeclaredFields();
        for (Field field:fieldArray
        ) {
            fieldSimpleTypeList.add(field.getType().getSimpleName());
        }
        return fieldSimpleTypeList;
    }

    @SuppressWarnings("unused")
    @Override
    public List<String> getFieldTypeList(Class<?> mClass){
        List<String> fieldTypeList=new ArrayList<>();
        //遍历字段集合
        Field[] fieldArray=mClass.getDeclaredFields();
        for (Field field:fieldArray
        ) {
            fieldTypeList.add(field.getType().getName());
        }
        return fieldTypeList;
    }
}
