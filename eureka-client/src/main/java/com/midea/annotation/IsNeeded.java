package com.midea.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 是否需要从解析excel赋值
 * @author: yangjun.ou
 * @date: 2019-03-15
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = {ElementType.FIELD})
public @interface IsNeeded {
    /**
     * 是否需要从解析excel赋值
     * @return true:需要  false:不需要
     */
    boolean isNeeded() default true;

}
