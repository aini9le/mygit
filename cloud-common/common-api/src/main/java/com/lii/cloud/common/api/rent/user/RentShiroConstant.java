package com.lii.cloud.common.api.rent.user;

import com.lii.cloud.common.api.ShiroConstant;
import com.lii.cloud.common.api.rent.ApiConstant;

/**
 * Rent业务功能的路径与权限配置
 * @author Administrator
 */
public class RentShiroConstant {
	// rent 应用服务 前半部分 既  rent:
	public static final String SHIRO_PREFIX = ShiroConstant.SERVICE_SHIRO_RENT + ShiroConstant.SHIRO_DELIMITER;
	
	// --------------------------  user业务模块 begin  ---------------------------------
	// user业务模块 前缀
	public static final String shiro_user_prefix=SHIRO_PREFIX + ApiConstant.RENT_USER + ShiroConstant.SHIRO_DELIMITER;
	// 用户 添加权限    rent:user:add
    public static final String shiro_user_add = "add";
    // 用户 编辑修改权限    rent:user:edit
    public static final String shiro_user_edit = "edit";
    // 用户 查询权限    rent:user:list
    public static final String shiro_user_list = "list";
    // 用户 查询单个信息 权限    rent:user:account
    public static final String shiro_user_account =  "account";
    // 用户 查询单个信息 权限    rent:user:userById
    public static final String shiro_user_querUserById = "querUserById";
    // 用户 根据id删除 权限    rent:user:deleteById
    public static final String shiro_user_deleteUserById = "deleteUserById";
    
    // --------------------------  user业务模块 end  ---------------------------------
    
    // --------------------------  role业务模块 begin  ---------------------------------
 	// user业务模块 前缀
 	private static final String shiro_role_prefix=SHIRO_PREFIX + ApiConstant.RENT_ROLE + ShiroConstant.SHIRO_DELIMITER;
 	
    // --------------------------  role业务模块 end  ---------------------------------
    
}
