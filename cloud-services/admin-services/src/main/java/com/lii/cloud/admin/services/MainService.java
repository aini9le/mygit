package com.lii.cloud.admin.services;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lii.cloud.common.entity.admin.po.MenuInfo;
import com.lii.cloud.common.entity.admin.vo.SuperUIMenuVo;
import com.lii.cloud.db.mysql.mapper.imapper.admin.MenuInfoMapper;

import tk.mybatis.mapper.entity.Example;


@Service
public class MainService {
	@Resource
	private MenuInfoMapper menuMapper;

	public JSONArray findSuperUIMenus(){
		Example ex = new Example(MenuInfo.class);
		ex.orderBy("menuSort").asc();
		List<MenuInfo> menus = menuMapper.selectByExample(ex);
		JSONArray json = new JSONArray();
		JSONObject js = new JSONObject();
		js.put("id", "Admin0001");
		js.put("text", "我的工作台");
		js.put("isHeader", true);
		json.add(js);
		for (MenuInfo m : menus) {
			if(null != m && "0".equals(m.getSupId())){
				json.add(menuToJSONObject(m,menus));
			}
		}
		return json;
	}
	
	/**
	 * 生成下级菜单集合
	 * @param model
	 * @param menus
	 * @return
	 */
	private SuperUIMenuVo menuToJSONObject(MenuInfo model,List<MenuInfo> menus){
		SuperUIMenuVo ui = newSuperUIMenuVo(model);
		for (MenuInfo m : menus) {
			if(model.getId().equals(m.getSupId())){
				if(null != m && !"0".equals(m.getSupId()) && !"B".equals(m.getMenuType())){ //排除一级菜单 和 按钮
					ui.addChildren(menuToJSONObject(m,menus)); //递归 生成下级菜单
				}
			}
		}
		return ui;
	}
	
	/**
	 * 统一创建对象 方便管理
	 * @param model
	 * @return
	 */
	private SuperUIMenuVo newSuperUIMenuVo(MenuInfo model){
		SuperUIMenuVo ui = new SuperUIMenuVo();
		if(null != model){
			ui.setId(model.getId());
			ui.setText(model.getMenuName());
			ui.setTargetType(model.getTargetType());
			if(StringUtils.isNotEmpty(model.getMenuUrl()))
				ui.setUrl(model.getMenuUrl());
			ui.setIcon(model.getIconcls());
		}
		return ui;
	}
	
	
}
