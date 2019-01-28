package com.lii.cloud.common.base.core.mongo;

public class BrowserEntity {
	
	private String name;  //浏览器名称
	private String type;  //类型
	private String version;  //版本
	private String manufacturer;  //生产厂家
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
}
