package com.xingyun.annotations.processor.interfaces;

public interface IDBTableXMLHandler {
    /**
     * 获取注解的数据库表名
     * @param mClass
     * @return
     */
    @SuppressWarnings("unused")
    String getDBTableName(Class<?> mClass);
}
