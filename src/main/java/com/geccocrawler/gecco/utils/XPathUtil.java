package com.geccocrawler.gecco.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.dom4j.Document;
import org.dom4j.Element;
import org.reflections.ReflectionUtils;

import com.geccocrawler.gecco.annotation.XPath;
import com.geccocrawler.gecco.spider.SpiderBean;
import com.geccocrawler.gecco.spider.conversion.Conversion;

import net.sf.cglib.beans.BeanMap;

public class XPathUtil {

	public static final Logger logger = Logger.getLogger(XPathUtil.class);

	/**
	 * 从给定element中取得xpath指定的object<br>
	 * 可以返回list，node，string等。具体情况和xml内容相关
	 * 
	 * @param ele
	 * @param xpath
	 * @return
	 */
	public static Object selectObject(Element ele, String xpath) {
		Object ret = ele.selectObject(xpath);
		return ret;
	}

	/**
	 * 填充数据<br>
	 * 本方法为通用方法，不支持嵌套，即xpath不能注解pojo类
	 * 
	 * @param doc
	 *            document
	 * @param bean
	 *            使用xPath注解的pojo类
	 */
	public static void inject(Document doc, SpiderBean bean) {
		Map<String, Object> fieldMap = new HashMap<String, Object>();
		BeanMap beanMap = BeanMap.create(bean);
		Set<Field> xpathField = ReflectionUtils.getAllFields(bean.getClass(),
				ReflectionUtils.withAnnotation(XPath.class));
		for (Field field : xpathField) {
			XPath annXpath = field.getAnnotation(XPath.class);
			String xpath = annXpath.value();
			Object obj = doc.selectObject(xpath);
			Object fieldValue = null;
			try {
				fieldValue = Conversion.getValue(field.getType(), obj);
			} catch (Exception e) {
				logger.error("conversion the field value error! fieldName=" + field.getName());
				throw new RuntimeException(e);
			}
			if (obj != null) {
				fieldMap.put(field.getName(), fieldValue);
			}
		}
		beanMap.putAll(fieldMap);
	}

}
