package com.lii.cloud.admin.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.lii.cloud.admin.services.UserService;
import com.lii.cloud.admin.tools.UIPageInfo;
import com.lii.cloud.common.base.concroller.BaseConcroller;
import com.lii.cloud.common.entity.admin.po.User;
import com.lii.cloud.common.entity.base.dto.BasePageInfoExampleDTO;
import com.lii.cloud.common.tools.result.ResultBody;

/**
 * 用户
 * @author liyao Create at:2018年5月28日 下午7:08:39
 */
@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseConcroller<User>{
	@Resource
	private UserService userService;

	/**
	 * 分页查询
	 * @param exDto
	 * @return
	 */
	@RequestMapping(value = "dataGrid")
	@ResponseBody
	public ResultBody dataGrid(BasePageInfoExampleDTO exDto) {
		UIPageInfo ui = new UIPageInfo();
		PageInfo<User> page = userService.dataGridExample(User.class, exDto);
		ui.setRows(page.getList());
		ui.setTotal(page.getTotal());
		return ResultBody.success("",ui);
	}

}
