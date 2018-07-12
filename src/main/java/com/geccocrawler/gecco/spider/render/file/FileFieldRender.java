package com.geccocrawler.gecco.spider.render.file;

import java.io.File;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.reflections.ReflectionUtils;

import com.geccocrawler.gecco.annotation.FileRootPath;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Image;
import com.geccocrawler.gecco.downloader.DownloaderContext;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.response.HttpResponse;
import com.geccocrawler.gecco.spider.FileBean;
import com.geccocrawler.gecco.spider.SpiderBean;
import com.geccocrawler.gecco.spider.render.FieldRender;
import com.geccocrawler.gecco.spider.render.FieldRenderException;
import com.geccocrawler.gecco.utils.DownloadImage;
import com.geccocrawler.gecco.utils.ReflectUtils;

import net.sf.cglib.beans.BeanMap;

public class FileFieldRender implements FieldRender {

	@Override
	public void render(HttpRequest request, HttpResponse response, BeanMap beanMap, SpiderBean bean) {
		Map<String, Object> fieldMap = new HashMap<String, Object>();
		Set<Field> rootPathFields = ReflectionUtils.getAllFields(bean.getClass(),
				ReflectionUtils.withAnnotation(FileRootPath.class));
		for (Field rootField : rootPathFields) {
			Object value = injectFileField(request, response, rootField);
			if (value != null) {
				fieldMap.put(rootField.getName(), value);
			}
		}
		beanMap.putAll(fieldMap);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Object injectFileField(HttpRequest request, HttpResponse response, Field field) {
		FileRootPath rootPath = field.getAnnotation(FileRootPath.class);
		String path = rootPath.rootPath();
		if (!path.endsWith(File.separator)) {
			path = path + File.separator;
		}

		String before = StringUtils.substringBefore(request.getUrl(), "?");
		String fileName = StringUtils.substringAfterLast(before, "/");
		try {
			String filePath = DownloadImage.download(path, fileName, response.getRaw());
			return filePath;
		} catch (Exception e) {
			FieldRenderException.log(field, "download file error!fileUrl=" + request.getUrl(), e);
		}
		return "";
	}
}
