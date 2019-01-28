package com.lii.cloud.common.tools.result.extend;

import com.lii.cloud.common.tools.result.ResponseInfo;

/**
 * 错误信息  的详细信息
 * @author Administrator
 *
 */
public class ExceptionResponseInfo implements ResponseInfo {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String [] messagesInfo;

	public String [] getMessagesInfo() {
		return messagesInfo;
	}

	public void setMessagesInfo(String [] messagesInfo) {
		this.messagesInfo = messagesInfo;
	}

}
