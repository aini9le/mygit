package com.lii.cloud.common.base.concroller;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.lii.cloud.common.api.base.BaseUrl;
import com.lii.cloud.common.base.services.BaseServiceImpl;
import com.lii.cloud.common.entity.base.dto.BaseIdDTO;
import com.lii.cloud.common.entity.base.dto.BasePageInfoDTO;
import com.lii.cloud.common.entity.base.dto.BasePageInfoExampleDTO;
import com.lii.cloud.common.entity.base.dto.BaseParameterIdDTO;
import com.lii.cloud.common.tools.result.ResultBody;
import com.lii.cloud.common.tools.result.defaults.DefaultObjectResponseInfo;
import com.lii.cloud.common.tools.result.extend.PageResponseInfo;

/**
 * 需要登录   公用控制器
 * @author Administrator
 * @param <T>
 */
public abstract class BaseConcroller<T> {
	
	@Autowired
	private BaseServiceImpl<T> baseService;
	
	/**
	 * 保存 或者 修改数据
	 * @param t
	 * @param bindingResult
	 * @return
	 * @throws Exception 
	 */
	@PostMapping(value="/"+BaseUrl.saveOrUpdate,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultBody saveOrUpdate(@RequestBody T t,BindingResult bindingResult) throws Exception{
		if (bindingResult.hasErrors()) {
            return ResultBody.error(bindingResult.getFieldError().getDefaultMessage());
        }
		if(baseService.saveOrUpdate(t) > 0){
			return ResultBody.success("数据编辑成功");
		}
		return ResultBody.error("编辑失败");
	}

	/**
	 * 根据id查询数据
	 * @param id
	 * @return
	 */
	@GetMapping(value="/"+BaseUrl.findById,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultBody findById(@Valid BaseParameterIdDTO baseDTO,BindingResult bindingResult){
		if (bindingResult.hasErrors()) {
            return ResultBody.error(bindingResult.getFieldError().getDefaultMessage());
        }
		if(StringUtils.isNotBlank(baseDTO.getId())){
			T data = baseService.findById(baseDTO.getId());
			return ResultBody.success(ResultBody.QUERY_SUCCESS, new DefaultObjectResponseInfo(data));
		}
		return ResultBody.error("检索失败");
	}
	
	/**
	 * 根据ids 删除数据
	 * @param ids
	 * @return
	 */
	@PostMapping(value="/"+BaseUrl.removesById,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultBody removesById(@RequestBody BaseIdDTO baseDTO,BindingResult bindingResult){
		if (bindingResult.hasErrors()) {
            return ResultBody.error(bindingResult.getFieldError().getDefaultMessage());
        }
		if(baseService.removesById(baseDTO.getId()) > 0){
			return ResultBody.success("删除数据对象成功");
		}
		return ResultBody.success("已成功执行语句，没有受影响的行！！！");
	}
	
	/**
	 * 根据id 删除数据
	 * @param ids
	 * @return
	 */
	@PostMapping(value="/"+BaseUrl.removesByIds,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultBody removesByIds(Class<T> t,@RequestBody BaseIdDTO baseDTO,BindingResult bindingResult){
		if (bindingResult.hasErrors()) {
            return ResultBody.error(bindingResult.getFieldError().getDefaultMessage());
        }
		if(null == t){
			t = buildClass();
		}
		if(baseService.removesByIds(t, baseDTO.getIds()) > 0){
			return ResultBody.success("批量删除对象数据成功");
		}
		return ResultBody.success("已成功执行语句，没有受影响的行！！！");
	}
	
	/**
	 * 可以进行条件 查询分页数据
	 * @param pageInfoDto
	 * @param bindingResult
	 * @return
	 */
	@GetMapping(value="/"+BaseUrl.findPageInfoExample,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultBody findPageInfoExample(@Valid BasePageInfoExampleDTO exDto,BindingResult bindingResult){
		if (bindingResult.hasErrors()) {
            return ResultBody.error(bindingResult.getFieldError().getDefaultMessage());
        }
		Class<T> c = buildClass();
		PageInfo<T> dataGrid = baseService.dataGridExample(c, exDto);
		return ResultBody.success(ResultBody.QUERY_SUCCESS, new PageResponseInfo<T>(dataGrid));
	}
	
	/**
	 * 查询分页数据
	 * @param pageInfoDto
	 * @param bindingResult
	 * @return
	 */
	@GetMapping(value="/"+BaseUrl.findPageInfoAll,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultBody findPageInfoAll(@Valid BasePageInfoDTO exDto,BindingResult bindingResult){
		if (bindingResult.hasErrors()) {
            return ResultBody.error(bindingResult.getFieldError().getDefaultMessage());
        }
		Class<T> c = buildClass();
		PageInfo<T> dataGrid = baseService.dataGridAll(c, exDto);
		return ResultBody.success(ResultBody.QUERY_SUCCESS, new PageResponseInfo<T>(dataGrid));
	}
	
	/**
	 * 获取子类 class 中传递的 T  class
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Class<T> buildClass(){
		Class<T> c = null;
		Type t = getClass().getGenericSuperclass();    
		ParameterizedType p = (ParameterizedType) t ;    
		c = (Class<T>) p.getActualTypeArguments()[0];
		return c;
	}
}
