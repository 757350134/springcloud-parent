package com.midea.annotation;

import java.lang.annotation.*;

/**
 * 
 * 系统对mybatis返回的map字段自动驼峰转换
 * 如果需要忽略自动转换，请在相关的mapper方法上添加忽略的字段名
 */

@Inherited
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IgnoreFields {
    
    /**
     * 忽略驼峰转换的字段名(匹配时，不区分大小写)
     */
    String[] value() default {};
    
    /**
     * 忽略全部，全部字段都不做驼峰转换
     * 如果ignore All 为true时，value里的列表被忽略
     */
    boolean ignoreAll() default false;
    
    /**
     * 只针对被忽略驼峰转换的字段名是否强转为小写再返回
     */
    boolean forceLowerCase() default false;
    
}
