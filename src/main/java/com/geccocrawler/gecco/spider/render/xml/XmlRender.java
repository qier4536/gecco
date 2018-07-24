package com.geccocrawler.gecco.spider.render.xml;

import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.response.HttpResponse;
import com.geccocrawler.gecco.spider.SpiderBean;
import com.geccocrawler.gecco.spider.render.AbstractRender;

import net.sf.cglib.beans.BeanMap;

/***
 * xml文件对应的解析类
 * 
 * @author novelbio
 *
 */
public class XmlRender extends AbstractRender {

	private XPathFieldRender xPathFieldRender;

	public XmlRender() {
		super();
		xPathFieldRender = new XPathFieldRender();
	}

	@Override
	public void fieldRender(HttpRequest request, HttpResponse response, BeanMap beanMap, SpiderBean bean) {
		xPathFieldRender.render(request, response, beanMap, bean);
	}

}
