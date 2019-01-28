package com.lii.cloud.common.entity.admin.vo;

import com.alibaba.fastjson.JSONArray;

public class SuperUIMenuVo {
	
	private String id;
	private String text;   //显示文本
	private String icon;  //图标
	private String url;  //路径
	private boolean isHeader = false;  // 是否是Header ,默认fasle
	private String targetType;  // 打开类型
	private JSONArray children = new JSONArray();  //下级菜单
	
	/**
	 * 添加下级菜单
	 * @param menu
	 */
	public void addChildren(SuperUIMenuVo menu){
		this.children.add(menu);
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public boolean isHeader() {
		return isHeader;
	}

	public void setHeader(boolean isHeader) {
		this.isHeader = isHeader;
	}

	public String getTargetType() {
		return targetType;
	}
	public void setTargetType(String targetType) {
		this.targetType = targetType;
	}
	public JSONArray getChildren() {
		return children;
	}
	public void setChildren(JSONArray children) {
		this.children = children;
	}

}
