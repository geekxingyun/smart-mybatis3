package com.xingyun.annotations.model;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义表注解
 */
//作用于类级别
@Target({ElementType.TYPE})
//运行时保留该注解
@Retention(RetentionPolicy.RUNTIME)
public @interface DBTable {
    String name() default "";
}