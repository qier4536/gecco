package com.geccocrawler.gecco.spider.render.file;

import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.response.HttpResponse;
import com.geccocrawler.gecco.spider.SpiderBean;
import com.geccocrawler.gecco.spider.render.AbstractRender;

import net.sf.cglib.beans.BeanMap;

public class FileRender extends AbstractRender {

	private FileFieldRender fileFieldRender;

	public FileRender() {
		super();
		this.fileFieldRender = new FileFieldRender();
	}

	@Override
	public void fieldRender(HttpRequest request, HttpResponse response, BeanMap beanMap, SpiderBean bean) {
		fileFieldRender.render(request, response, beanMap, bean);
	}

}