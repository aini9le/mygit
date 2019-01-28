package com.lii.cloud.common.base.interfaces;

import org.springframework.web.servlet.ModelAndView;

public interface BaseAdminInterface {
	
	/**
	 * 返回主页  
	 * @return
	 */
	public ModelAndView index();
	
	/**
	 * 返回编辑主页
	 * @param obj
	 * @return
	 */
	public ModelAndView editIndex(String id);

}
