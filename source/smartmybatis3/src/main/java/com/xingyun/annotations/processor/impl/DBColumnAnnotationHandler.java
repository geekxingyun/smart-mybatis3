package com.xingyun.annotations.processor.impl;

import com.xingyun.annotations.model.DBColumn;
import com.xingyun.annotations.processor.interfaces.IDBColumnAnnotationHandler;

import java.lang.reflect.Field;

public class DBColumnAnnotationHandler implements IDBColumnAnnotationHandler {

    @Override
    public String autoCreateMappingModel(Class<?> mClass){
        StringBuilder sb=new StringBuilder();
        sb.append("@Results({\r\n");
        //遍历字段集合
        Field[] fieldArray=mClass.getDeclaredFields();
        for (Field field:fieldArray
             ) {
            DBColumn dbColumn=field.getAnnotation(DBColumn.class);
            String fieldName=field.getName();
            String dbColumnName=null;
            if(null!=dbColumn){
                dbColumnName=dbColumn.name();
            }
            String fieldType=field.getType().getSimpleName();
            sb.append(getCommonResultLine(fieldName,dbColumnName,fieldType));
        }
        sb.append("})");
        return sb.toString();
    }
    private String getCommonResultLine(String property,String column,String type){
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("\r\t");
        stringBuilder.append("@Result(property =");
        stringBuilder.append("\"");
        stringBuilder.append(property);
        stringBuilder.append("\"");
        stringBuilder.append(",");
        stringBuilder.append("column =");
        stringBuilder.append("\"");
        stringBuilder.append(column);
        stringBuilder.append("\"");
        stringBuilder.append(",");
        stringBuilder.append("javaType=");
        stringBuilder.append(type);
        stringBuilder.append(".class");
        stringBuilder.append(")");
        stringBuilder.append(",");
        stringBuilder.append("\r\n");
        return stringBuilder.toString();
    }
}

