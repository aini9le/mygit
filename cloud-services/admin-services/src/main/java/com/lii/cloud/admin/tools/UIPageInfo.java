package com.lii.cloud.admin.tools;

import java.util.List;

import com.lii.cloud.common.tools.result.ResponseInfo;

/**
 * 后端管理框架分页信息
 * @author Administrator
 *
 */
public class UIPageInfo implements ResponseInfo{
	
	private Long total;  // 数据总条数
	private List<?> rows;//数据
	
	public UIPageInfo() {
	}
	
	public UIPageInfo(Long total, List<?> rows) {
		this.total = total;
		this.rows = rows;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public List<?> getRows() {
		return rows;
	}
	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	
}
