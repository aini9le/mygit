package com.lii.cloud.common.base.core.mongo;

import java.util.Date;

import javax.persistence.Column;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

@Document(collection = "businessLogEntity")
public class BusinessLogEntity {
	
	@Column(name="_id")
	private String id; //
	private String tenantId; //租户id
	private String executive;  //执行人
	private String type;  //类型
	private String action;  //动作
	private String message; //消息体
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss:sss",timezone="GMT+8")
	private Date create;   //创建时间
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	public String getExecutive() {
		return executive;
	}
	public void setExecutive(String executive) {
		this.executive = executive;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getCreate() {
		return create;
	}
	public void setCreate(Date create) {
		this.create = create;
	}

}
