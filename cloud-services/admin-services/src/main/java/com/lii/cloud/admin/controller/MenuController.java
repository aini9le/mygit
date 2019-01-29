package com.lii.cloud.admin.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.lii.cloud.admin.dto.MenuDto;
import com.lii.cloud.admin.services.MenuService;
import com.lii.cloud.admin.tools.UIPageInfo;
import com.lii.cloud.common.base.concroller.BaseConcroller;
import com.lii.cloud.common.entity.admin.po.MenuInfo;
import com.lii.cloud.common.entity.base.dto.BasePageInfoExampleDTO;
import com.lii.cloud.common.tools.result.ResultBody;

@Controller
@RequestMapping(value = "/menu")
public class MenuController extends BaseConcroller<MenuInfo> {
	@Resource
	private MenuService menuService;

	@RequestMapping(value = "/dataGrid")
	@ResponseBody
	public ResultBody dataGrid(BasePageInfoExampleDTO exDto) {
		UIPageInfo ui = new UIPageInfo();
		PageInfo<MenuInfo> page = menuService.dataGridExample(MenuInfo.class, exDto);
		ui.setRows(page.getList());
		ui.setTotal(page.getTotal());
		return ResultBody.success("",ui);
	}

}
