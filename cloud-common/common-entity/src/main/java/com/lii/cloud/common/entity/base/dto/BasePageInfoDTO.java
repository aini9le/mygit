package com.lii.cloud.common.entity.base.dto;

import javax.validation.constraints.NotNull;


/**
 * 分页查询公用 数据传递对象
 * @author Administrator
 *
 */
public class BasePageInfoDTO{

	@NotNull(message = "查询页数不能为空")
	private Integer pageNumber = 1;  //当前页 默认为1
	private Integer pageSize = 10;   // 每页大小  默认10
	private String sort;   //排序字段
	private String sortOrder; //排序方式
	private Integer startCount;//起始条数
	
	public Integer getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
	public Integer getStartCount() {
		return (pageNumber-1)*pageSize;
	}
	public void setStartCount(Integer startCount) {
		this.startCount = startCount;
	}
	
}
