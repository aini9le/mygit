package com.lii.cloud.common.base.core.exception;

import com.lii.cloud.common.tools.result.ResponseInfo;

/**
 */
public class RentException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	private ResponseInfo responseInfo;

    public RentException(ResponseInfo responseInfo) {
        this.responseInfo = responseInfo;
    }

    public ResponseInfo getResponseInfo() {
        return responseInfo;
    }

    public void setResponseInfo(ResponseInfo responseInfo) {
        this.responseInfo = responseInfo;
    }
}
