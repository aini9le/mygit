package com.lii.cloud.common.tools.result.defaults;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lii.cloud.common.tools.result.ResponseInfo;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DefaultObjectResponseInfo implements ResponseInfo {
	
	private static final long serialVersionUID = 2995979663585365330L;
	
	private Object data; //接收任何类型的数据

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public DefaultObjectResponseInfo(Object data) {
		super();
		this.data = data;
	}
}
