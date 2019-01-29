package com.lii.cloud.admin.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.lii.cloud.admin.services.RoleService;
import com.lii.cloud.admin.tools.UIPageInfo;
import com.lii.cloud.common.base.concroller.BaseConcroller;
import com.lii.cloud.common.base.interfaces.BaseAdminInterface;
import com.lii.cloud.common.entity.admin.po.Role;
import com.lii.cloud.common.entity.base.dto.BaseIdDTO;
import com.lii.cloud.common.entity.base.dto.BasePageInfoExampleDTO;
import com.lii.cloud.common.entity.base.dto.BaseParameterIdDTO;
import com.lii.cloud.common.tools.result.ResultBody;

/**
 * 用户
 * @author liyao Create at:2018年5月28日 下午7:08:39
 */
@Controller
@RequestMapping(value = "/role")
public class RoleController extends BaseConcroller<Role> implements BaseAdminInterface {
	private static final String BASEURL = "pages/role/";
	@Resource
	private RoleService roleService;

	/**
	 * 初始页面
	 */
	@Override
	@RequestMapping(value = "index")
	public ModelAndView index() {
		ModelAndView mode = new ModelAndView(BASEURL + "index");
		return mode;
	}

	/**
	 * 跳转修改或新增页面
	 */
	@Override
	@RequestMapping(value = "editIndex")
	public ModelAndView editIndex(String id) {
		ModelAndView mode = new ModelAndView(BASEURL + "editIndex");
		try {
			Role role = roleService.findById(id);
			if (role == null) {
				role = new Role();
			}
			mode.addObject("role", role);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return mode;
	}

	/**
	 * 通过I获取用户
	 * @param dto
	 * @param bindingResult
	 * @return
	 */
	@RequestMapping(value = "findById")
	public ModelAndView findRoleById(@Valid BaseParameterIdDTO dto, BindingResult bindingResult) {
		ModelAndView mode = new ModelAndView(BASEURL + "index");
		if (bindingResult.hasErrors()) {
			return mode;
		}
		Role role = roleService.findById(dto.getId());
		mode.addObject("role", role);
		return mode;
	}

	/**
	 * 分页查询
	 * @param exDto
	 * @return
	 */
	@RequestMapping(value = "dataGrid")
	@ResponseBody
	public UIPageInfo dataGrid(BasePageInfoExampleDTO exDto) {
		UIPageInfo ui = new UIPageInfo();
		PageInfo<Role> page = roleService.dataGridExample(Role.class, exDto);
		ui.setRows(page.getList());
		ui.setTotal(page.getTotal());
		return ui;
	}

	/**
	 * 删除用户
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "removes")
	@ResponseBody
	public ResultBody removes(@Valid BaseIdDTO dto) throws Exception {
		roleService.removesByIds(Role.class, dto.getIds());
		return ResultBody.success("");
	}

	/**
	 * 保存用户
	 * @param Role
	 * @return
	 */
	@RequestMapping(value = "save")
	@ResponseBody
	public ResultBody save(@ModelAttribute(value = "role") Role role) throws Exception {
		roleService.saveOrUpdate(role);
		return ResultBody.success("");
	}

}
