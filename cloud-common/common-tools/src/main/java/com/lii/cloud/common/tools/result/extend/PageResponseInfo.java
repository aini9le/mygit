package com.lii.cloud.common.tools.result.extend;

import com.github.pagehelper.PageInfo;
import com.lii.cloud.common.tools.result.ResponseInfo;

public class PageResponseInfo<T> implements ResponseInfo {
	
	private PageInfo<T> pages;  // 分页的数据

	public PageInfo<T> getPages() {
		return pages;
	}

	public void setPages(PageInfo<T> pages) {
		this.pages = pages;
	}

	public PageResponseInfo(PageInfo<T> pages) {
		super();
		this.pages = pages;
	} 
}
