package com.xingyun.sample;

import com.xingyun.SmartMyBatis3App;
import com.xingyun.annotations.processor.interfaces.IDBColumnAnnotationHandler;
import com.xingyun.annotations.processor.interfaces.IDBColumnXMLHandler;
import com.xingyun.annotations.processor.interfaces.IDBTableXMLHandler;
import com.xingyun.annotations.processor.interfaces.ISmartObjectHandler;
import com.xingyun.sample.model.UserInfo;

import java.util.List;

public class UserInfoTest {

    public static void main(String[] args) {
        UserInfoTest userInfoTest=new UserInfoTest();
        userInfoTest.test();
    }

    IDBTableXMLHandler iDBTableXMLHandler;

    IDBColumnXMLHandler idbColumnXMLHandler;

    IDBColumnAnnotationHandler iDBColumnAnnotationHandler;

    ISmartObjectHandler iSmartObjectHandler;

    private void test(){
        //打印MyBatis3 映射表名
        iDBTableXMLHandler= SmartMyBatis3App.getDBTableXMLHandler();
        String dbTableName=iDBTableXMLHandler.getDBTableName(UserInfo.class);
        System.out.println("----打印MyBatis3 映射表名开始----");
        System.out.println(dbTableName);
        System.out.println("----打印MyBatis3 映射表名结束----");

        //打印MyBatis3 XML实体类映射Map
        idbColumnXMLHandler= SmartMyBatis3App.getDBColumnXMLHandler();
        System.out.println("----打印MyBatis3 XML实体类映射Map开始----");
        idbColumnXMLHandler.printDocument(UserInfo.class);
        System.out.println("----打印MyBatis3 XML实体类映射Map结束----");

        //打印MyBatis3 注解实体类映射Map
        iDBColumnAnnotationHandler= SmartMyBatis3App.getDBColumnAnnotationHandler();
        String result=iDBColumnAnnotationHandler.autoCreateMappingModel(UserInfo.class);
        System.out.println("----打印MyBatis3 注解实体类映射Map开始----");
        System.out.println(result);
        System.out.println("----打印MyBatis3 注解实体类映射Map结束---");

        //打印UserInfo 对象字段集合
        iSmartObjectHandler=SmartMyBatis3App.getSmartObjectHandler();
        List<String> fieldNameList=iSmartObjectHandler.getFieldNameList(UserInfo.class);
        System.out.println("----打印UserInfo 对象字段集合开始---");
        for (String fieldName:fieldNameList
             ) {
            System.out.println(fieldName);
        }
        System.out.println("----打印UserInfo 对象字段集合结束---");

        //打印 带包名的字段类型集合
        List<String> fieldTypeList=iSmartObjectHandler.getFieldTypeList(UserInfo.class);
        System.out.println("----打印 带包名的字段类型集合开始---");
        for (String fieldType:fieldTypeList
        ) {
            System.out.println(fieldType);
        }
        System.out.println("----打印 带包名的字段类型集合结束---");

        //打印 不带包名的字段类型集合
        List<String> fieldSimpleTypeList=iSmartObjectHandler.getFieldSimpleTypeList(UserInfo.class);
        System.out.println("----打印 不带包名的字段类型集合开始---");
        for (String fieldSimpleType:fieldSimpleTypeList
        ) {
            System.out.println(fieldSimpleType);
        }
        System.out.println("----打印 不带包名的字段类型集合结束---");

        //打印 注解映射表名的字段类型集合
        List<String> dbColumnNameList=iSmartObjectHandler.getDBColumnList(UserInfo.class);
        System.out.println("----打印 注解映射表名的字段类型集合开始---");
        for (String dbColumnName:dbColumnNameList
        ) {
            System.out.println(dbColumnName);
        }
        System.out.println("----打印 注解映射表名的字段类型集合结束---");
    }
}
