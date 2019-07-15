package com.midea.annotation;

import java.lang.annotation.*;


/**
 * 不需要打印日志的注解
 * 建议分页列表，打上该注解，params=true，result=false，因为分页打印出参会很多log，以免挤爆内存
 * @author 欧阳俊
 * @date 2019年3月7日
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NoLog {
    /**
     * 不记录入参
     */
    boolean params() default false;

    /**
     * 不记录返回值
     */
    boolean result() default false;

    /**
     * 不记录异常
     */
    boolean exception() default false;
}