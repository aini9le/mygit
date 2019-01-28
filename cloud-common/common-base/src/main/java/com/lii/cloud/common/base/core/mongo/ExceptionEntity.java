package com.lii.cloud.common.base.core.mongo;

import java.util.Date;
import java.util.HashMap;

import javax.persistence.Column;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lii.cloud.common.tools.result.extend.ExceptionResponseInfo;

/**
 * 封装的错误数据结构
 * @author Administrator
 *
 */

@Document(collection = "exceptionEntity")
public class ExceptionEntity {
	
	@Column(name="_id")
	private String id; //
	
	private String ip;   //ip地址
	private String path;  //请求路径
	private String method; //请求方式
	
	private BrowserEntity browser; //浏览器
	private OperatingSystemEntity system;  //系统
	
	private HashMap<String, Object> headers; // 所有的header数据集合
	private HashMap<String, Object> parameters; // 所有的 Parameter 数据集合
	private String body;   //request body数据
	
	private String message;  //错误信息
	private ExceptionResponseInfo exceptionInfo;//错误详情数据
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss:sss",timezone="GMT+8")
	private Date create;   //创建时间

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public HashMap<String, Object> getHeaders() {
		return headers;
	}

	public void setHeaders(HashMap<String, Object> headers) {
		this.headers = headers;
	}

	public HashMap<String, Object> getParameters() {
		return parameters;
	}

	public void setParameters(HashMap<String, Object> parameters) {
		this.parameters = parameters;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ExceptionResponseInfo getExceptionInfo() {
		return exceptionInfo;
	}

	public void setExceptionInfo(ExceptionResponseInfo exceptionInfo) {
		this.exceptionInfo = exceptionInfo;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getCreate() {
		return create;
	}

	public void setCreate(Date create) {
		this.create = create;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public BrowserEntity getBrowser() {
		return browser;
	}

	public void setBrowser(BrowserEntity browser) {
		this.browser = browser;
	}

	public OperatingSystemEntity getSystem() {
		return system;
	}

	public void setSystem(OperatingSystemEntity system) {
		this.system = system;
	}
	
}
