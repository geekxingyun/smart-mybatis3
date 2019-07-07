package com.xingyun.annotations.model;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DBColumn {
    //数据库字段的名称
    String name() default "";
    //数据库字段的长度
    long length() default 255;
    //数据库字段的类型
    String type() default "varchar";
    //数据库字段说明
    String description() default "";
}
