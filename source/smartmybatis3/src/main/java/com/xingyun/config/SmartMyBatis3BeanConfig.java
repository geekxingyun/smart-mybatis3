package com.xingyun.config;

import com.xingyun.annotations.processor.impl.DBColumnAnnotationHandler;
import com.xingyun.annotations.processor.impl.DBColumnXMLHandler;
import com.xingyun.annotations.processor.impl.DBTableXMLHandler;
import com.xingyun.annotations.processor.impl.SmartObjectHandler;
import com.xingyun.annotations.processor.interfaces.IDBColumnAnnotationHandler;
import com.xingyun.annotations.processor.interfaces.IDBColumnXMLHandler;
import com.xingyun.annotations.processor.interfaces.IDBTableXMLHandler;
import com.xingyun.annotations.processor.interfaces.ISmartObjectHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SmartMyBatis3BeanConfig {

    @Bean
    public IDBTableXMLHandler idbTableXMLHandler(){
        return new DBTableXMLHandler();
    }

    @Bean
    public IDBColumnXMLHandler idbColumnXMLHandler(){
        return new DBColumnXMLHandler();
    }

    @Bean
    public IDBColumnAnnotationHandler idbColumnAnnotationHandler(){
        return new DBColumnAnnotationHandler();
    }

    @Bean
    public ISmartObjectHandler iSmartObjectHandler(){
        return new SmartObjectHandler();
    }
}
