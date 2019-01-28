package com.lii.cloud.common.base.core.exception;

import com.lii.cloud.common.tools.result.ResponseInfo;

/**
 * 所有业务异常的枚举
 */
public enum RentExceptionEnum  implements ResponseInfo {
    /**
     * 账号问题
     */
    USER_ALREADY_REG("40001", "该用户已经注册"),
    USER_NOT_EXISTED("40002", "没有此用户"),
    USER_PWD_ERROR("40003", "密码不正确"),
    USER_ERROR_EXCESSIVE("40004", "错误次数过多"),
	USER_LOCK("40005","用户已锁定");

    private String code;
    private String message;

    RentExceptionEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}

