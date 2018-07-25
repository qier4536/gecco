package com.geccocrawler.gecco.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.reflections.ReflectionUtils;

import com.geccocrawler.gecco.annotation.XPath;
import com.geccocrawler.gecco.annotation.XPathObjct;
import com.geccocrawler.gecco.spider.XmlBean;
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
	 * 本方法为通用方法，支持解析xpath，和xpathObject。时间解析不完整
	 * 
	 * @param Element
	 *            root 跟元素
	 * @param bean
	 *            使用xPath注解的pojo类
	 */
	public static void injectXmlBean(Element root, XmlBean bean) {
		Map<String, Object> fieldMap = new HashMap<String, Object>();
		BeanMap beanMap = BeanMap.create(bean);

		// 解析xpath
		Set<Field> xpathFields = ReflectionUtils.getAllFields(bean.getClass(),
				ReflectionUtils.withAnnotation(XPath.class));
		for (Field field : xpathFields) {
			XPath annXpath = field.getAnnotation(XPath.class);
			String xpath = annXpath.value();
			Object obj = root.selectSingleNode(xpath);
			Object fieldValue = null;
			if (obj != null) {
				String text = ((Element) obj).getStringValue();
				try {
					fieldValue = Conversion.getValue(field.getType(), text);
					fieldMap.put(field.getName(), fieldValue);
				} catch (Exception e) {
					logger.error("conversion the field value error! fieldName=" + field.getName());
					throw new RuntimeException(e);
				}
			}
		}

		// 解析xpathObject
		Set<Field> xpathObjctFields = ReflectionUtils.getAllFields(bean.getClass(),
				ReflectionUtils.withAnnotation(XPathObjct.class));
		for (Field field : xpathObjctFields) {
			try {
				Object obj = injectXPathObjectField(root, field);
				if (obj != null) {
					fieldMap.put(field.getName(), obj);
				}
			} catch (Exception e) {
				logger.error("rend the xmlPathObject error!", e);
			}
		}
		beanMap.putAll(fieldMap);
	}

	/**
	 * 填充xpathObjet注解的字段，直接返回对象，不需要在转换类型
	 * 
	 * @param element
	 * @param field
	 * @return
	 * @throws Exception
	 */
	public static Object injectXPathObjectField(Element root, Field field) throws Exception {
		Object retObj = null;

		Class<?> type = field.getType();// field属性的类
		boolean isArray = type.isArray();
		boolean isList = ReflectUtils.haveSuperType(type, List.class);// 是List类型
		boolean isMap = ReflectUtils.haveSuperType(type, Map.class); // 是否是map类型
		boolean isXmlBean = ReflectUtils.haveSuperType(type, XmlBean.class);
		if (isList) { // 列表
			Type genericType = field.getGenericType();// 获得包含泛型的类型
			Class genericClass = ReflectUtils.getGenericClass(genericType, 0);// 泛型类
			retObj = listRender(root, genericClass, field);
			return retObj;
		} else if (isArray) { // array
			Class genericClass = type.getComponentType();
			List<Object> lsObj = listRender(root, genericClass, field);
			retObj = lsObj.toArray();
		} else {
			XPathObjct xpathObj = field.getAnnotation(XPathObjct.class);
			String rootPath = xpathObj.rootPath();
			Element ele = (Element) root.selectSingleNode(rootPath);
			if (isMap) { // map类型
				Map<String, Object> map = new HashMap<>();
				for (Object obj : ele.elements()) {
					Element eleObj = (Element) obj;
					map.put(eleObj.getName(), eleObj.getStringValue());
				}
				retObj = map;
			} else if (isXmlBean) { // xmlbean类型
				XmlBean bean = (XmlBean) type.newInstance();
				injectXmlBean(ele, bean);
				retObj = bean;
			}
		}
		return retObj;
	}

	/**
	 * 处理list，array类型的xml数据
	 * 
	 * @param root
	 * @param genericClass
	 * @param field
	 * @return
	 * @throws Exception
	 */
	private static List<Object> listRender(Element root, Class genericClass, Field field) throws Exception {
		List<Object> lsobj = new ArrayList<>();

		XPathObjct xpathObj = field.getAnnotation(XPathObjct.class);
		String rootPath = xpathObj.rootPath();
		String listPath = xpathObj.listPath();
		boolean isXmlBean = ReflectUtils.haveSuperType(genericClass, XmlBean.class);
		List<Node> lsNodes = root.selectNodes(rootPath);
		for (Node node : lsNodes) {
			Element ele = (Element) node;
			if (isXmlBean) { // list<xmlBean>类型处理
				XmlBean bean = (XmlBean) genericClass.newInstance();
				injectXmlBean(ele, bean);
				lsobj.add(bean);
			} else { // list<string>,list<integer> 等基本类型对应的封装类型
				Object listObj = node;
				if (StringUtils.isNotBlank(listPath)) {
					listObj = ele.selectSingleNode(listPath);
				}
				if (listObj != null) {
					String text = ((Element) listObj).getStringValue();
					try {
						Object value = Conversion.getValue(genericClass, text);
						lsobj.add(value);
					} catch (Exception e) {
						logger.error("rend the list error!list genericClass=" + genericClass + "----field="
								+ field.getName());
						throw e;
					}
				}
			}
		}
		return lsobj;
	}
}
