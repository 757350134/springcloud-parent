package com.midea.annotation;


import java.lang.annotation.*;


@Inherited
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME) // 注解在JVM加载class后继续保持有效
public @interface DataSourceStrategy {
	
	/**
	 * 如果支持系统级别多数据源，则先指定系统数据源的Bean ID后再做长短连接切换
	 * Bean ID请参考applicationContext-base-datasource.xml的MultipleDataSource支持，不指定时默认是ccsDataSource
	 */
	String dataSourceId() default "ccsDataSource";
	
}
