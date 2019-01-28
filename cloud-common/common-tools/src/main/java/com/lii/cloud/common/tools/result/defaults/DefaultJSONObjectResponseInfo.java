package com.lii.cloud.common.tools.result.defaults;

import com.alibaba.fastjson.JSONObject;
import com.lii.cloud.common.tools.result.ResponseInfo;

/**
 * 默认的返回数据对象
 * @author Administrator
 *
 */
public class DefaultJSONObjectResponseInfo implements ResponseInfo {

	private static final long serialVersionUID = 6950399198431510574L;
	
	private JSONObject data; //使用json存储数据

	public JSONObject getData() {
		return data;
	}

	public void setData(JSONObject data) {
		this.data = data;
	}

	public DefaultJSONObjectResponseInfo(JSONObject data) {
		super();
		this.data = data;
	}
	
}
