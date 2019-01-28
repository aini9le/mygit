package com.lii.cloud.common.tools.https;

import com.alibaba.fastjson.JSONObject;

public class HttpClientJSONResponse {
	
	private Integer status;
	private JSONObject response;
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public JSONObject getResponse() {
		return response;
	}
	public void setResponse(JSONObject response) {
		this.response = response;
	}
	
}
