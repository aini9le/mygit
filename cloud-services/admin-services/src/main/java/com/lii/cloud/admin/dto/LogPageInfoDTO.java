package com.lii.cloud.admin.dto;

import com.lii.cloud.common.entity.base.dto.BasePageInfoDTO;

/**
 * 日志请求参数
 */
public class LogPageInfoDTO extends BasePageInfoDTO {
	
	private String path; //路径
	private String ip;   //ip地址
	private String where; //条件
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getWhere() {
		return where;
	}
	public void setWhere(String where) {
		this.where = where;
	}
	
}
