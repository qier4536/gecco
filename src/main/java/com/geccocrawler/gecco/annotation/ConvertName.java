package com.geccocrawler.gecco.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 重命名字段的注解<br>
 * tsv,csv文本类解析时使用，注解的字段为修改后的字段，name表示tsv,csv中的列名
 * 
 * @author novelbio
 *
 */
@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ConvertName {

	String title();
}
