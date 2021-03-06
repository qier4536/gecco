package com.geccocrawler.gecco.spider.render;

import org.reflections.Reflections;

import net.sf.cglib.proxy.Enhancer;

import com.geccocrawler.gecco.monitor.RenderMointorIntercetor;
import com.geccocrawler.gecco.spider.render.file.FileRender;
import com.geccocrawler.gecco.spider.render.html.HtmlRender;
import com.geccocrawler.gecco.spider.render.json.JsonRender;
import com.geccocrawler.gecco.spider.render.xml.XmlRender;

public class MonitorRenderFactory extends RenderFactory {

	public MonitorRenderFactory(Reflections reflections) {
		super(reflections);
	}

	@Override
	public HtmlRender createHtmlRender() {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(HtmlRender.class);
		enhancer.setCallback(new RenderMointorIntercetor());
		return (HtmlRender) enhancer.create();
	}

	@Override
	public JsonRender createJsonRender() {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(JsonRender.class);
		enhancer.setCallback(new RenderMointorIntercetor());
		return (JsonRender) enhancer.create();
	}

	@Override
	public FileRender createFileRender() {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(FileRender.class);
		enhancer.setCallback(new RenderMointorIntercetor());
		return (FileRender) enhancer.create();
	}

	@Override
	public XmlRender createXmlRender() {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(XmlRender.class);
		enhancer.setCallback(new RenderMointorIntercetor());
		return (XmlRender) enhancer.create();
	}

}
