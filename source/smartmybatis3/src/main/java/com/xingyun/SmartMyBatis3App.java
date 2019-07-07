package com.xingyun;

import com.xingyun.annotations.processor.interfaces.IDBColumnAnnotationHandler;
import com.xingyun.annotations.processor.interfaces.IDBColumnXMLHandler;
import com.xingyun.annotations.processor.interfaces.IDBTableXMLHandler;
import com.xingyun.annotations.processor.interfaces.ISmartObjectHandler;
import com.xingyun.config.SmartMyBatis3BeanConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SuppressWarnings("unused")
public class SmartMyBatis3App {

    //初始化上下文
    public static final ApplicationContext applicationContext=new AnnotationConfigApplicationContext(SmartMyBatis3BeanConfig.class);

    public static  final IDBTableXMLHandler getDBTableXMLHandler(){
        return applicationContext.getBean(IDBTableXMLHandler.class);
    }
    public static  final IDBColumnAnnotationHandler getDBColumnAnnotationHandler(){
        return applicationContext.getBean(IDBColumnAnnotationHandler.class);
    }
    public static  final IDBColumnXMLHandler getDBColumnXMLHandler(){
        return applicationContext.getBean(IDBColumnXMLHandler.class);
    }
    public static  final ISmartObjectHandler getSmartObjectHandler(){
        return applicationContext.getBean(ISmartObjectHandler.class);
    }
}
