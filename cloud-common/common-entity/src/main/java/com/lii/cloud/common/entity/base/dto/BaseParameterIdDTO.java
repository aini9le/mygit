package com.lii.cloud.common.entity.base.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 数据传输对象  公用属性
 * @author Administrator
 *
 */
public class BaseParameterIdDTO implements BaseParameterDTO,Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "ID不能为空")
//	@Size(min = 1, max = 20, message = "id长度为1-20")
	private String id;   // id主键
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
