package com.geccocrawler.gecco.spider.render;

import java.util.HashMap;
import java.util.Map;

import org.reflections.Reflections;

import com.geccocrawler.gecco.spider.render.file.FileRender;
import com.geccocrawler.gecco.spider.render.html.HtmlRender;
import com.geccocrawler.gecco.spider.render.json.JsonRender;
import com.geccocrawler.gecco.spider.render.xml.XmlRender;

public abstract class RenderFactory {

	private Map<RenderType, Render> renders;

	public RenderFactory(Reflections reflections) {
		CustomFieldRenderFactory customFieldRenderFactory = new CustomFieldRenderFactory(reflections);
		renders = new HashMap<RenderType, Render>();

		AbstractRender htmlRender = createHtmlRender();
		htmlRender.setCustomFieldRenderFactory(customFieldRenderFactory);

		AbstractRender jsonRender = createJsonRender();
		jsonRender.setCustomFieldRenderFactory(customFieldRenderFactory);

		AbstractRender FileRender = createFileRender();
		FileRender.setCustomFieldRenderFactory(customFieldRenderFactory);

		AbstractRender xmlRender = createXmlRender();

		renders.put(RenderType.HTML, htmlRender);
		renders.put(RenderType.JSON, jsonRender);
		renders.put(RenderType.File, FileRender);
		renders.put(RenderType.XML, xmlRender);
	}

	public Render getRender(RenderType type) {
		return renders.get(type);
	}

	public abstract HtmlRender createHtmlRender();

	public abstract JsonRender createJsonRender();

	public abstract FileRender createFileRender();

	public abstract XmlRender createXmlRender();

}
