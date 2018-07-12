package com.geccocrawler.gecco.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 声明的字段记录文件下载后的路径
 * 用于下载文件时确认fileBean文件的下载根路径<br>
 * 
 * @author liqi
 *
 */
@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FileSavePath {

	/**
	 * 用于下载文件时确认fileBean文件的下载根路径
	 * 
	 * @return
	 */
	String rootPath();
}
