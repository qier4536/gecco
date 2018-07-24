package com.geccocrawler.gecco.utils;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;

/**
 * JSONPath工具类，爬取信息时，缓存JSONPath对象
 * 
 * @author novelbio
 *
 */
public class JSONPathUtil {
	private Map<String, JSONPath> mapJsonPathCache = new HashMap<>();

	/**
	 * 获取指定path的string值
	 * 
	 * @param json
	 * @param path
	 * @return
	 */
	public String getString(JSONObject json, String path) {
		return getObject(json, path, String.class);
	}

	/**
	 * 获取指定path的JSONObject值
	 * 
	 * @param json
	 * @param path
	 * @return
	 */
	public JSONObject getJSONObject(JSONObject json, String path) {
		return getObject(json, path, JSONObject.class);
	}

	/**
	 * 获取指定path的JSONArray值
	 * 
	 * @param json
	 * @param path
	 * @return
	 */
	public JSONArray getJSONArray(JSONObject json, String path) {
		return getObject(json, path, JSONArray.class);
	}

	/**
	 * 获取json指定path中的数据，并转为指定的类型。<br>
	 * 如果类型不匹配(相等，或者是子类)，返回null
	 * 
	 * @param json
	 * @param path
	 * @param clazz
	 * @return
	 */
	public <T> T getObject(JSONObject json, String path, Class<T> clazz) {
		JSONPath cachePath = mapJsonPathCache.get(path);
		if (cachePath == null) {
			cachePath = JSONPath.compile(path);
			mapJsonPathCache.put(path, cachePath);
		}
		Object objValue = cachePath.eval(json);
		if (objValue != null) {
			if (objValue.getClass().equals(clazz) || ReflectUtils.haveSuperType(objValue, clazz)) {
				return clazz.cast(objValue);
			}
		}
		return null;
	}
}
