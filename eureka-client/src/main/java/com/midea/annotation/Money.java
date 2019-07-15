package com.midea.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface Money {
    /**定义是否可转换单位*/
    boolean unitConversion() default false;
}
