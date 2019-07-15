package com.midea.annotation;

import java.lang.annotation.*;

/**
 * @author
 * @since 
 * 
 */
@Target({ElementType.METHOD, ElementType.TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {
	// 该值即key值
	String value() default "master";
}
