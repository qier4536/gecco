package com.geccocrawler.gecco.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 解析json中的array数组中的内容,注解在一个list类型的字段上
 * 
 * @author novelbio
 *
 */
@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface JSONArrayPath {
	/**
	 * 解析json中的array对象
	 * 
	 * @return
	 */
	String arrayPath();

	/**
	 * 以arrayPath中的对象为根，获取指定的字段
	 * 
	 * @return
	 */
	String valuePath() default "";

}
