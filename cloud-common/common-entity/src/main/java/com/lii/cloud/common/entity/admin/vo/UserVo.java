package com.lii.cloud.common.entity.admin.vo;

import java.util.List;

import com.lii.cloud.common.entity.admin.po.User;
import com.lii.cloud.common.tools.result.ResponseInfo;

public class UserVo extends User implements ResponseInfo{
	
	private List<User> list;

	public List<User> getList() {
		return list;
	}

	public void setList(List<User> list) {
		this.list = list;
	}
	
	

}
