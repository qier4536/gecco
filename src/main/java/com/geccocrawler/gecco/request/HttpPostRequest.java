package com.geccocrawler.gecco.request;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * fields 为post提交的数据map
 * @author novelbio
 *
 */
public class HttpPostRequest extends AbstractHttpRequest {

	private static final long serialVersionUID = -4451221207994730839L;

	public HttpPostRequest() {
		super();
	}

	public HttpPostRequest(String url) {
		super(url);
	}
	
	public static HttpPostRequest fromJson(JSONObject request) {
		return (HttpPostRequest)JSON.toJavaObject(request, HttpPostRequest.class);
	}
}
