package com.lii.cloud.admin.dto;

import org.hibernate.validator.constraints.NotBlank;

import com.lii.cloud.common.entity.base.dto.BaseParameterIdDTO;

public class MenuDto extends BaseParameterIdDTO{

	private static final long serialVersionUID = -7744521662173411905L;
	@NotBlank(message = "启用或者暂停不能为空")
	private String isEnable;  //是否启用
	
	public String getIsEnable() {
		return isEnable;
	}
	public void setIsEnable(String isEnable) {
		this.isEnable = isEnable;
	}
}
