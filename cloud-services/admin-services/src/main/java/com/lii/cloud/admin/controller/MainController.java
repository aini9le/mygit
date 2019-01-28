package com.lii.cloud.admin.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.lii.cloud.admin.services.MainService;

@Controller
@RequestMapping(value="/")
public class MainController {
	private static final String BASEURL = "main";
	
	@Resource
	private MainService mainService;
	
	@RequestMapping(value="index")
	public String index(){
		System.out.println("................");
		return BASEURL + "/index";
	}

	@RequestMapping(value="/menuSuperUiIndex")
	@ResponseBody
	public JSONArray menuSuperUiIndex(){
		JSONArray json = mainService.findSuperUIMenus();
		System.out.println(json.toJSONString());
		return json;
	}
}
