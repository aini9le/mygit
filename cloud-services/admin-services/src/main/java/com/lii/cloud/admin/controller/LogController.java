package com.lii.cloud.admin.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lii.cloud.admin.dto.LogPageInfoDTO;
import com.lii.cloud.admin.services.LogService;
import com.lii.cloud.admin.tools.UIPageInfo;
import com.lii.cloud.common.tools.result.ResultBody;

@Controller
@RequestMapping("/log")
public class LogController {
	
	private static final String BASEURL = "page/sysLog/";
	
	@Autowired
	private LogService logService;
	
	@RequestMapping(value="/getLog")
	@ResponseBody
	public UIPageInfo getLog(LogPageInfoDTO logDto){
		return logService.getLog(logDto);
	}
	
	@RequestMapping(value="/index")
	public ModelAndView index() {
		ModelAndView model = new ModelAndView(BASEURL + "Index");
		return model;
	}
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public ResultBody remove(LogPageInfoDTO logDto){
		if(null == logDto){
			return ResultBody.error("请传递参数");
		}
		if(StringUtils.isBlank(logDto.getWhere())){
			return ResultBody.error("请选择清除时间段");
		}
		logService.remove(logDto);
		return ResultBody.success("成功清除数据");
	}

}
