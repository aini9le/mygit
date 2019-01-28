package com.lii.cloud.common.tools.result.defaults;

import com.alibaba.fastjson.JSONArray;
import com.lii.cloud.common.tools.result.ResponseInfo;

public class DefaultJSONArrayResponseInfo implements ResponseInfo {
	
	private static final long serialVersionUID = -877010464879803667L;
	
	private JSONArray data;  //使用json数组接收对象

	public JSONArray getData() {
		return data;
	}

	public void setData(JSONArray data) {
		this.data = data;
	}

	public DefaultJSONArrayResponseInfo(JSONArray data) {
		super();
		this.data = data;
	}
    
}
