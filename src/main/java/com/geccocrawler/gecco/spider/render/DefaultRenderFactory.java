package com.geccocrawler.gecco.spider.render;

import org.reflections.Reflections;

import com.geccocrawler.gecco.spider.render.file.FileRender;
import com.geccocrawler.gecco.spider.render.html.HtmlRender;
import com.geccocrawler.gecco.spider.render.json.JsonRender;
import com.geccocrawler.gecco.spider.render.xml.XmlRender;

public class DefaultRenderFactory extends RenderFactory {
	
	public DefaultRenderFactory(Reflections reflections) {
		super(reflections);
	}

	public HtmlRender createHtmlRender() {
		return new HtmlRender();
	}
	
	public JsonRender createJsonRender() {
		return new JsonRender();
	}

	public FileRender createFileRender() {
		return new FileRender();
	}

	@Override
	public XmlRender createXmlRender() {
		return new XmlRender();
	}
	
}
