package com.geccocrawler.gecco.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * 标记的字段需要为Scheduler的子类，否则不起作用<br>
 * spiderBean中添加自定义注解表示，后续的抓取计划由用户定制规则<br>
 * <p>
 * 一个类中只能有一个CustomScheduler注解
 * 
 * @author novelbio
 *
 */
@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomScheduler {

}
