package com.xingyun.annotations.processor.impl;

import com.xingyun.annotations.model.DBTable;
import com.xingyun.annotations.processor.interfaces.IDBTableXMLHandler;

@SuppressWarnings("unused")
public class DBTableXMLHandler implements IDBTableXMLHandler {
    /**
     * 获取注解的数据库表名
     * @param mClass
     * @return
     */
    @Override
    public String getDBTableName(Class<?> mClass){
        DBTable dbTable=mClass.getAnnotation(DBTable.class);
        if(null!=dbTable){
            return dbTable.name();
        }
        return null;
    }
}

