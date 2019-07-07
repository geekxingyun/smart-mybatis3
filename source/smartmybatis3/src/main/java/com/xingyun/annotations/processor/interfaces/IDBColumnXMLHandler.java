package com.xingyun.annotations.processor.interfaces;

import org.dom4j.Document;

public interface IDBColumnXMLHandler {
    /**
     * 創建Mapper.xml模板文件
     * @param mClass
     * @return
     */
    Document createDocument(Class<?> mClass);

    /**
     * 打印输出Document
     * @param mClass
     */
    void printDocument(Class<?> mClass);
}
