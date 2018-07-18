package com.geccocrawler.gecco.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * @author liqi
 *
 */
public class ContentTypeUtils {

	/**
	 * 是否为文本类型<br>
	 * 可以解析成文本的信息<br>
	 * 带有"charset","text","json","javascript"的为text
	 * 
	 * @param contentType
	 * @return
	 */
	public static boolean isText(String contentType) {
		String[] arrStr = { "charset", "text", "json", "javascript" };
		return contain(contentType, arrStr);
	}

	/**
	 * 是否包含数组中的字符，包含任意一个字符返回true<br>
	 * 统一转成小写匹配
	 * 
	 * @param contentType
	 * @param arrStr
	 * @return
	 */
	public static boolean contain(String contentType, String[] arrStr) {
		if (StringUtils.isBlank(contentType)) {
			return false;
		}

		// 使用小写匹配
		String lowContentType = contentType.toLowerCase();
		for (String string : arrStr) {
			if (lowContentType.indexOf(string.toLowerCase()) > -1) {
				return true;
			}
		}
		return false;
	}

}
