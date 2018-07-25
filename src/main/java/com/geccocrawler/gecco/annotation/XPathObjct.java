package com.geccocrawler.gecco.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * XML格式文件注解,支持封装的注解，包括map，list，继承xmlBean的object等<br>
 * map格式，xml标签名为key，内容为value<br>
 * list格式，获取xml中指定的xpath中的指定字段(必须使用相对路径)。 继承xmlBean的object，在指定的xml元素中继续解析
 * 
 * @author novelbio
 *
 */
@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface XPathObjct {
	/**
	 * 指向xml结构体的xpath，作为后续listXPath的根
	 * 
	 * @return
	 */
	String rootPath();

	/**
	 * 当field为list时，指定list中的获取的具体内容的xpath（必须使用xpath相对路径）<br>
	 * 使用”/“，”//“将会从整个document中查找
	 * 
	 * @return
	 */
	String listPath() default "";

}
