package com.lii.cloud.common.tools.https;

public abstract class HttpClientResponse {
	
	private Integer status;
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public abstract String getResponse();
	public abstract void setResponse(String response);
	
}
