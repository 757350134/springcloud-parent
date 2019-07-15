package com.midea.annotation;

import java.lang.annotation.*;

/**
 * 
 * 支持时间戳和数字自增 2种版本控制的方式
 * 
 * 请使用 VersionControlHelper.setVersionControl() 代替@VersionControl
 * 
 */

@Deprecated

@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface VersionControl {
	
	// 版本号控制使用的JAVA字段(支持JAVA Timestamp/Date/Long/Integer)
	String field() default "lastUpdateDate";
	
	// 版本号控制使用的DB字段
	String column() default "LAST_UPDATE_DATE";
	
}
