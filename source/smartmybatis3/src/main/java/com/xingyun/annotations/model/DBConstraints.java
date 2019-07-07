package com.xingyun.annotations.model;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义字段约束注解
 */
//作用于成员变量字段
@Target(ElementType.FIELD)
//运行时保留该注解
@Retention(RetentionPolicy.RUNTIME)
public @interface DBConstraints {
    //是否是主键
    boolean primaryKey() default false;
    //是否唯一
    boolean unique() default false;
    //是否允许为空
    boolean allowNull() default true;
}
