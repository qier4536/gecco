package com.geccocrawler.gecco.spider.render.xml;

import java.io.StringReader;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.response.HttpResponse;
import com.geccocrawler.gecco.spider.SpiderBean;
import com.geccocrawler.gecco.spider.XmlBean;
import com.geccocrawler.gecco.spider.render.FieldRender;
import com.geccocrawler.gecco.utils.XPathUtil;

import net.sf.cglib.beans.BeanMap;

public class XPathFieldRender implements FieldRender {

	private static final Logger logger = Logger.getLogger(XPathFieldRender.class);

	@Override
	public void render(HttpRequest request, HttpResponse response, BeanMap beanMap, SpiderBean bean) {
		StringReader reader = new StringReader(response.getContent());
		SAXReader saxReader = new SAXReader();

		Document document = null;
		try {
			document = saxReader.read(reader);
		} catch (DocumentException e) {
			logger.error("parse the xml file error, url=" + request.getUrl());
			throw new RuntimeException(e);
		}
		XPathUtil.injectXmlBean(document.getRootElement(), (XmlBean) bean);
		
	}

}
