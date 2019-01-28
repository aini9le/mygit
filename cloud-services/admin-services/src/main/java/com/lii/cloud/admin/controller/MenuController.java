package com.lii.cloud.admin.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.lii.cloud.admin.dto.MenuDto;
import com.lii.cloud.admin.services.MenuService;
import com.lii.cloud.admin.tools.UIPageInfo;
import com.lii.cloud.common.base.concroller.BaseConcroller;
import com.lii.cloud.common.base.interfaces.BaseAdminInterface;
import com.lii.cloud.common.entity.admin.po.MenuInfo;
import com.lii.cloud.common.entity.base.dto.BasePageInfoExampleDTO;
import com.lii.cloud.common.tools.result.ResultBody;

@Controller
@RequestMapping(value = "/sysMenu")
public class MenuController extends BaseConcroller<MenuInfo> implements BaseAdminInterface {
	private static final String BASEURL = "pages/menu/";
	@Resource
	private MenuService menuService;

	@Override
	@RequestMapping(value = "/index")
	public ModelAndView index() {
		ModelAndView model = new ModelAndView(BASEURL + "index");
		return model;
	}

	@Override
	@RequestMapping(value = "/editIndex")
	public ModelAndView editIndex(String id) {
		ModelAndView model = new ModelAndView(BASEURL + "editIndex");
		MenuInfo menu = new MenuInfo();
		if (StringUtils.isNotBlank(id)) {
			menu = menuService.findById(id);
		}
		model.addObject("menu", menu);
		return model;
	}

	@RequestMapping(value = "/editSelectIndex")
	public ModelAndView editSelectIndex() {
		ModelAndView model = new ModelAndView(BASEURL + "editSelectIndex");
		return model;
	}

	/**
	 * 启用或者暂停
	 * 
	 * @param menuDto
	 * @param bindingResult
	 * @return
	 */
	@RequestMapping(value = "/isEnableUrl")
	@ResponseBody
	public ResultBody isEnableUrl(@Valid MenuDto menuDto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return ResultBody.error(bindingResult.getFieldError().getDefaultMessage());
		}
		if (menuService.isEnableUrl(menuDto) > 0) {
			return ResultBody.success("数据编辑成功");
		}
		return ResultBody.error("已执行，0行受影响");
	}

	@RequestMapping(value = "/dataGrid")
	@ResponseBody
	public UIPageInfo dataGrid2(BasePageInfoExampleDTO exDto) {
		UIPageInfo ui = new UIPageInfo();
		PageInfo<MenuInfo> page = menuService.dataGridExample(MenuInfo.class, exDto);
		ui.setRows(page.getList());
		ui.setTotal(page.getTotal());
		return ui;
	}

}
