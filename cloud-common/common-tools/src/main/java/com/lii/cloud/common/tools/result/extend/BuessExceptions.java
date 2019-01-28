package com.lii.cloud.common.tools.result.extend;

import com.lii.cloud.common.tools.result.ResponseInfo;

/**
 * 描述：自定义异常提示
 *
 * @Author: xiaoyong
 * @Date: 2018-11-27
 */
public class BuessExceptions extends RuntimeException implements ResponseInfo {

    private String code;  //默认 1500
    private String body; //消息体

    public BuessExceptions() {
    }

    public BuessExceptions(String body) {
        this.body = body;
    }

    public BuessExceptions(String code, String body) {
        this.code = code;
        this.body = body;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
