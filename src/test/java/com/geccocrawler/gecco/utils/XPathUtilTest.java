package com.geccocrawler.gecco.utils;

import static org.junit.Assert.*;

import java.io.StringReader;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import com.geccocrawler.gecco.utils.sub.SubXmlBean;

public class XPathUtilTest {

	private String xml = "<root><list>list1</list><list>list2</list><list>list3</list>"
			+ "<list1><sub1>lsSub11</sub1><sub2>lsSub12</sub2></list1><list1><sub1>lsSub21</sub1><sub2>lsSub22</sub2></list1>"
			+ "<list1><sub1>lsSub31</sub1><sub2>lsSub32</sub2></list1><map><key1>value1</key1><key2>value2</key2></map>"
			+ "<title>test Title</title>"
			+ "<location><name>name1</name><email>email1</email></location><location><name>name2</name><email>email2</email></location>"
			+ "<primaryLocation><name>name0</name><email>email0</email></primaryLocation></root>";

	@Test
	public void test() throws DocumentException {
		StringReader reader = new StringReader(xml);
		SAXReader saxReader = new SAXReader();
		Document document = saxReader.read(reader);

		SubXmlBean bean = new SubXmlBean();
		XPathUtil.injectXmlBean(document.getRootElement(), bean);

		assertEquals("test Title", bean.getTitle());
		assertEquals("lsSub31", bean.getLsSub1().get(2));
		assertTrue(bean.getMapInfo().containsKey("key1"));
		assertEquals("email0", bean.getXmlBean().getEmail());
	}

}
