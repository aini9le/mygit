package com.lii.cloud.common.entity.base;

import java.util.Date;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonFormat;

public class BaseEntityIdDateOwner extends BaseEntityIdAndDate {

	private static final long serialVersionUID = 1L;
	
	private String creater; //创建人
	@Column(name="edit_date")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date editDate; //修改时间
	private String editer; //修改人
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	public Date getEditDate() {
		return editDate;
	}
	public void setEditDate(Date editDate) {
		this.editDate = editDate;
	}
	public String getEditer() {
		return editer;
	}
	public void setEditer(String editer) {
		this.editer = editer;
	}
}
