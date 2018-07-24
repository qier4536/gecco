package com.geccocrawler.gecco.spider.render.json;

import java.lang.reflect.Field;
import java.util.Set;

import org.reflections.ReflectionUtils;

import com.geccocrawler.gecco.annotation.JSONArrayPath;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.response.HttpResponse;
import com.geccocrawler.gecco.spider.SpiderBean;
import com.geccocrawler.gecco.spider.render.FieldRender;

import net.sf.cglib.beans.BeanMap;

public class JsonArrayFieldRender implements FieldRender {

	@Override
	public void render(HttpRequest request, HttpResponse response, BeanMap beanMap, SpiderBean bean) {
		Set<Field> jsonPathFields = ReflectionUtils.getAllFields(bean.getClass(),
				ReflectionUtils.withAnnotation(JSONArrayPath.class));
		
		
		

	}

}
