package com.xingyun.annotations.processor.impl;

import com.xingyun.annotations.model.DBColumn;
import com.xingyun.annotations.model.DBConstraints;
import com.xingyun.annotations.processor.interfaces.IDBColumnXMLHandler;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.IOException;
import java.lang.reflect.Field;

public class DBColumnXMLHandler implements IDBColumnXMLHandler {

    /**
     * 創建Mapper.xml模板文件
     * @param mClass
     * @return
     */
    @Override
    public Document createDocument(Class<?> mClass) {
        Document document = DocumentHelper.createDocument();
        document.addDocType("mapper","-//mybatis.org//DTD Mapper 3.0//EN","http://mybatis.org/dtd/mybatis-3-mapper.dtd");
        Element root = document.addElement("mapper");
        String nameSpace=mClass.getName()+"Mapper";
        root.addAttribute("namespace",nameSpace);
        //ResultMap结点
        Element resultMapElement = root.addElement("resultMap")
                .addAttribute("type", mClass.getName())
                .addAttribute("id", mClass.getSimpleName()+"ResultMap");
        //id 结点
        Element idElement;
        //result结点
        Element resultElement;
        //遍历字段集合
        Field[] fieldArray=mClass.getDeclaredFields();
        for (Field field:fieldArray
        ) {
            DBConstraints dbConstraints=field.getAnnotation(DBConstraints.class);
            DBColumn dbColumn=field.getAnnotation(DBColumn.class);
            String dbColumnName=null;
            if(null!=dbColumn){
                dbColumnName=dbColumn.name();
            }
            //有约束注解
            if(null!=dbConstraints){
                //不是主键
                if(dbConstraints.primaryKey()){
                    idElement=resultMapElement.addElement("id");
                    resultElement.addAttribute("column",dbColumnName);
                    resultElement.addAttribute("property",field.getName());
                }else{
                    resultElement=resultMapElement.addElement("result");
                    resultElement.addAttribute("column",dbColumnName);
                    resultElement.addAttribute("property",field.getName());
                }
            }else{
                 resultElement=resultMapElement.addElement("result");
                 resultElement.addAttribute("column",dbColumnName);
                 resultElement.addAttribute("property",field.getName());
            }
        }
        return document;
    }

    @Override
    public void printDocument(Class<?> mClass) {
       Document document=createDocument(mClass);
        try {
            // Pretty print the document to System.out
            OutputFormat format = OutputFormat.createPrettyPrint();
            XMLWriter writer = new XMLWriter(System.out, format);
            writer.write( document );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

