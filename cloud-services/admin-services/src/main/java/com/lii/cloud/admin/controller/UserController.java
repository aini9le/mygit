package com.lii.cloud.admin.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.lii.cloud.admin.services.UserService;
import com.lii.cloud.admin.tools.UIPageInfo;
import com.lii.cloud.common.base.concroller.BaseConcroller;
import com.lii.cloud.common.base.interfaces.BaseAdminInterface;
import com.lii.cloud.common.entity.base.dto.BasePageInfoExampleDTO;
import com.lii.cloud.common.entity.base.dto.BaseParameterIdDTO;

/**
 * 
 * 系统用户
 *
 * @author liyao
 * Create at:2018年5月28日 下午7:08:39
 */
@Controller
@RequestMapping(value = "/sysUser")
public class UserController extends BaseConcroller<User> implements BaseAdminInterface {
	private static final String BASEURL = "pages/user/";
	@Resource
	private UserService userService;

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
	 * 用户信息搜索页面
	 * @return
	 */
	@RequestMapping(value = "searchIndex")
	public ModelAndView searchIndex() {
		ModelAndView mode = new ModelAndView(BASEURL + "SearchUser");
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
			User user = userService.findById(id);
			if (user == null) {
				user = new User();
			}
			mode.addObject("user", user);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return mode;
	}

	/**
	 * 
	 * 通过I获取用户
	 * 
	 * @param dto
	 * @param bindingResult
	 * @return
	 */
	@RequestMapping(value = "findById")
	public ModelAndView findUserById(@Valid BaseParameterIdDTO dto, BindingResult bindingResult) {
		ModelAndView mode = new ModelAndView(BASEURL + "index");
		if (bindingResult.hasErrors()) {
			return mode;
		}
		User user = userService.findById(dto.getId());
		mode.addObject("user", user);
		return mode;
	}

	/**
	 * 
	 * 分页查询
	 * 
	 * @param exDto
	 * @return
	 */
	@RequestMapping(value = "dataGrid")
	@ResponseBody
	public UIPageInfo dataGrid(BasePageInfoExampleDTO exDto) {
		UIPageInfo ui = new UIPageInfo();
		PageInfo<User> page = userService.dataGridExample(User.class, exDto);
		ui.setRows(page.getList());
		ui.setTotal(page.getTotal());
		return ui;
	}

	/**
	 * 
	 *
	 * 删除用户
	 * 
	 * @param ids
	 *            用户ID
	 * @return
	 */
	@RequestMapping(value = "removes")
	@ResponseBody
	public Map<String,Object> removes(@Valid UserDto userDto) {
		Map<String, Object> obj = new HashMap<String, Object>();
		try {
			userService.removesByIds(User.class, userDto.getIds());
			obj.put("status", "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	/**
	 * 
	 * 保存用户
	 *
	 * @param User
	 * @return
	 */
	@RequestMapping(value = "save")
	@ResponseBody
	public Map<String,Object> save(@ModelAttribute(value = "user") User user) {
		Map<String, Object> obj = new HashMap<String, Object>();
		try {
			userService.saveOrUpdate(user);
			obj.put("status", "SUCCESS");
		} catch (Exception e) {
			obj.put("status", "-ERROR");
			e.printStackTrace();
		}
		return obj;
	}

}
