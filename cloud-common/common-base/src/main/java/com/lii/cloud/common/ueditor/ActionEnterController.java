package com.lii.cloud.common.ueditor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baidu.ueditor.ActionEnter;

@Controller
@RequestMapping("/ueditor")
public class ActionEnterController {
	
	@RequestMapping(value="/getActionEnter")
	@ResponseBody
	public String getActionEnter(@RequestParam("action") String param,HttpServletRequest request,HttpServletResponse response){
//		String rootPath = this.getClass().getResource("/static/plugins/").getPath();
//		System.out.println("更改前=" + rootPath);
//		rootPath = rootPath.replaceFirst("/", "");
		String rootPath = "D:/tools/fileUpload/";
		System.out.println("路径=" + rootPath);
//		String rootPath = application.getRealPath( "/" );
		String exec = new ActionEnter( request, rootPath  ).exec();
		System.out.println("配置信息=" + exec);
		return exec;
//		System.out.println("参数= " + param);
//		return ConfigJson.getConfig().toJSONString();
	}

}
