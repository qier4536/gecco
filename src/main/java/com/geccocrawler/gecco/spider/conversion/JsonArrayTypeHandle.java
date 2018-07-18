package com.geccocrawler.gecco.spider.conversion;

import com.alibaba.fastjson.JSONArray;

public class JsonArrayTypeHandle implements TypeHandle<JSONArray> {

	@Override
	public JSONArray getValue(Object src) throws Exception {

		return JSONArray.parseArray(src.toString());
	}

}
