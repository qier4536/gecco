package com.geccocrawler.gecco.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * XML格式文件注解，仅支持简单字段，如int，string，double，long等<br>
 * 不支持list，map，array，pojo对象等<br>
 * xpath需要写到最后一级
 * <p>
 * 嵌套使用时，即xmlBean中存在一个innerXmlBean对象。<br>
 * innerXmlBean的xpath不能使用”/“，”//“开头的xpath。<br>
 * 因为dom4j在使用”/“，”//“时将会从document开始查找。建议使用”./“开头的相对路径
 * 
 * 
 * @author novelbio
 *
 */
@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface XPath {

	String value();

}
