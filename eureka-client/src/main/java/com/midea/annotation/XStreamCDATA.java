package com.midea.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 是否需要在解析的xml里加上[cdata]转义
 * @author: yangjun.ou
 * @date: 2019-03-01
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface XStreamCDATA {

}