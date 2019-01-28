package com.lii.cloud.admin.services;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lii.cloud.admin.dto.MenuDto;
import com.lii.cloud.common.base.services.BaseServiceImpl;
import com.lii.cloud.common.entity.admin.po.MenuInfo;
import com.lii.cloud.db.mysql.mapper.imapper.admin.MenuInfoMapper;

@Service
public class MenuService extends BaseServiceImpl<MenuInfo>{
	
	@Resource
	private MenuInfoMapper menuMapper;
	
	public int isEnableUrl(MenuDto menuDto){
		MenuInfo menu = new MenuInfo();
		menu.setId(menuDto.getId());
		menu.setIsEnable(menuDto.getIsEnable());
		menu.setEnableDate(new Date());
		return menuMapper.updateByPrimaryKeySelective(menu);
	}

}
