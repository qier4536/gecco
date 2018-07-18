package com.geccocrawler.gecco.spider.conversion;

import com.alibaba.fastjson.JSONObject;

public class JsonObjectTypeHandle implements TypeHandle<JSONObject> {

	@Override
	public JSONObject getValue(Object src) throws Exception {
		return JSONObject.parseObject(src.toString());
	}

}
