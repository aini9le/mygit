package com.lii.cloud.common.entity.base.dto;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 默认的 扩展数据传输对象
 * @author Administrator
 *
 */
public class BaseDefalutExampleDTO {
	
	private ConcurrentHashMap<String, Object> columns; // 查询的列 集合

	public ConcurrentHashMap<String, Object> getColumns() {
		return columns;
	}

	public void setColumns(ConcurrentHashMap<String, Object> columns) {
		this.columns = columns;
	}
}
