package com.lii.cloud.db.mysql.mapper.imapper.admin;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lii.cloud.common.entity.admin.po.MenuInfo;
import com.lii.cloud.db.mysql.basis.TkMapper;

public interface MenuInfoMapper extends TkMapper<MenuInfo> {
	
	/**
	 * 通过角色id 查询菜单信息
	 * @param id
	 * @return
	 */
	public List<MenuInfo> selectByRoleId(@Param("roleId") String roleId);
}