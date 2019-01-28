package com.lii.cloud.common.base.services;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lii.cloud.common.base.tools.ExampleQueryUtil;
import com.lii.cloud.common.entity.base.dto.BasePageInfoDTO;
import com.lii.cloud.common.entity.base.dto.BasePageInfoExampleDTO;
import com.lii.cloud.common.tools.result.ResultBody;
import com.lii.cloud.db.mysql.basis.TkMapper;
import com.lii.cloud.db.redis.IdSeqGenerator;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class BaseServiceImpl<T>{
	@Autowired
	protected IdSeqGenerator idGen;
	
	@Autowired
	protected TkMapper<T> mapper;

	@Transactional(propagation= Propagation.REQUIRED,rollbackFor=Exception.class)
	public int saveOrUpdate(T t) throws Exception {
//		Method getMet = t.getClass().getDeclaredMethod("getId");//获取id的方法
//		Method setMet = t.getClass().getDeclaredMethod("setId", String.class);//设置id的方法
		Method getMet = t.getClass().getMethod("getId");//获取id的方法
		Method setMet = t.getClass().getMethod("setId", String.class);//设置id的方法
		Object id = getMet.invoke(t);  //获取id值
		int count = 0;
		if(null == id || StringUtils.isEmpty(id.toString())){
			setMet.invoke(t, idGen.nextId());  //给对象赋值
			count = mapper.insertSelective(t);
		}else{
			count = mapper.updateByPrimaryKeySelective(t);  // 修改
		}
		return count;
	}
	
	@Transactional(propagation= Propagation.REQUIRED,rollbackFor=Exception.class)
	public int saves(List<T> lists) throws Exception{
		int count = 0;
		for (T t : lists) {
//			Method setMet = t.getClass().getDeclaredMethod("setId", String.class);//设置id的方法
			Method setMet = t.getClass().getMethod("setId", String.class);//设置id的方法
			setMet.invoke(t, idGen.nextId());  //给对象赋值
			count += mapper.insert(t);  // 保存数据
		}
//		count = mapper.insertList(lists); // 批量操作  自定义id 不能使用此方法  只适合于 自增 uuid类主键策略
		return count;
	}

	/**
	 * 根据id 删除对象
	 * @param id
	 * @return
	 */
	public int removesById(String id){
		return mapper.deleteByPrimaryKey(id);
	}
	
	/**
	 * 通过id 批量删除对象数据
	 * @param t
	 * @param ids
	 * @return
	 */
	@Transactional(propagation= Propagation.REQUIRED,rollbackFor=Exception.class)
	public int removesByIds(Class<T> t, String ids) {
		// TODO 自动生成的方法存根
		Example example=new Example(t);
		Criteria criteria = example.createCriteria();
		criteria.andIn("id", Arrays.asList(StringUtils.split(ids,",")));
		int count = mapper.deleteByExample(example);
		return count;
	}

	/**
	 * 根据id 查询对象
	 * @param id
	 * @return
	 */
	public T findById(String id) {
		if(StringUtils.isNotEmpty(id)){
			return mapper.selectByPrimaryKey(id);
		}
		return null;
	}

	/**
	 * 分页数据查询
	 * @param c
	 * @param exDto
	 * @return
	 */
	@SuppressWarnings("resource")
	public PageInfo<T> dataGridExample(Class<T> c, BasePageInfoExampleDTO exDto) {
		if(null == exDto){
			return null;
		}
		Example ex = null;
		PageInfo<T> pageInfo = null;
		if(null != c){
			ex = new Example(c);
			if(StringUtils.isNotBlank(exDto.getSort()) && StringUtils.isNotBlank(exDto.getSort())){
				if("ASC".equalsIgnoreCase(exDto.getSortOrder())){
					ex.orderBy(exDto.getSort()).asc();
				}
				if("DESC".equalsIgnoreCase(exDto.getSortOrder())){
					ex.orderBy(exDto.getSort()).desc();
				}
			}
			Criteria cr = ex.createCriteria();
			ConcurrentHashMap<String, Object> columns = exDto.getColumns();  //获取其它传递的参数
			if(null != columns)
				for (Map.Entry<String, Object> entry : columns.entrySet()) {
					ExampleQueryUtil.exampleQuery(cr, entry.getKey().trim(), entry.getValue());
				}
			PageHelper.startPage(exDto.getPageNumber(), exDto.getPageSize(),true).countColumn("id");  // true：需要进行统计  根据id 进行统计
			List<T> list = mapper.selectByExample(ex);
			Page<T> page = (Page<T>) list;
			pageInfo = page.toPageInfo();
		}
		return pageInfo;
	}

	/**
	 * 所有数据分页查询
	 * @param c
	 * @param pageDto
	 * @return
	 */
	@SuppressWarnings("resource")
	public PageInfo<T> dataGridAll(Class<T> c,BasePageInfoDTO pageDto){
		PageInfo<T> pageInfo = null;
		if(null != c){
			PageHelper.startPage(pageDto.getPageNumber(), pageDto.getPageSize(),true).countColumn("id");  // true：需要进行统计  根据id 进行统计
			List<T> list = mapper.selectAll();
			Page<T> page = (Page<T>) list;
			pageInfo = page.toPageInfo();
		}
		return pageInfo;
	}
	
	/**
	 * 分页数据查询
	 * @param c
	 * @param exDto
	 * @param a
	 * @return
	 */
	@SuppressWarnings("resource")
	public PageInfo<T> dataGridExample(Class<T> c, BasePageInfoExampleDTO exDto,int a) {
		if(null == exDto){
			return null;
		}
		Example ex = null;
		PageInfo<T> pageInfo = null;
		if(null != c){
			ex = new Example(c);
			if(StringUtils.isNotBlank(exDto.getSort()) && StringUtils.isNotBlank(exDto.getSort())){
				if("ASC".equalsIgnoreCase(exDto.getSortOrder())){
					ex.orderBy(exDto.getSort()).asc();
				}
				if("DESC".equalsIgnoreCase(exDto.getSortOrder())){
					ex.orderBy(exDto.getSort()).desc();
				}
			}
			Criteria cr = ex.createCriteria();
			ConcurrentHashMap<String, Object> columns = exDto.getColumns();  //获取其它传递的参数
			if(null != columns)
				for (Map.Entry<String, Object> entry : columns.entrySet()) {
					ExampleQueryUtil.exampleQuery(cr, entry.getKey().trim(), entry.getValue());
				}
			PageHelper.startPage(exDto.getPageNumber(), exDto.getPageSize(),true).countColumn("id_");  // true：需要进行统计  根据id 进行统计
			List<T> list = mapper.selectByExample(ex);
			Page<T> page = (Page<T>) list;
			pageInfo = page.toPageInfo();
		}
		return pageInfo;
	}
	
	/**
	 * 新增或修改
	 * @param t
	 * @return
	 * @throws Exception
	 */
	@Transactional(propagation= Propagation.REQUIRED,rollbackFor=Exception.class)
	public ResultBody save(T t) throws Exception{
			Method getMet = t.getClass().getMethod("getId");//获取id的方法
			Method setMet = t.getClass().getMethod("setId", String.class);//设置id的方法
			boolean bool = true;
			Method setCreateTime = null;//创建时间
			try {
				 setCreateTime = t.getClass().getMethod("setCreateTime", Date.class);
			} catch (NoSuchMethodException e) {
				bool = false;
			}
			
			Object id = getMet.invoke(t);  //获取id值
			if(null == id || StringUtils.isEmpty(id.toString())){
				setMet.invoke(t, idGen.nextId());  //给对象赋值
				if(bool){
					setCreateTime.invoke(t, new Date());
				}
				if(mapper.insertSelective(t)>0){
					return ResultBody.success("新增成功");
				}else{
					return ResultBody.error("已成功执行语句，没有受影响的行！！！");
				}
			}else{
				if(mapper.updateByPrimaryKeySelective(t)>0){// 修改
					return ResultBody.success("修改成功");
				}else{
					return ResultBody.error("已成功执行语句，没有受影响的行！！！");
				}  
			}
	}
	
	/**
	 * 新增主键不为空
	 * @param t
	 * @return
	 * @throws Exception
	 */
	@Transactional(propagation= Propagation.REQUIRED,rollbackFor=Exception.class)
	public ResultBody insertSelective(T t) throws Exception{
			Method	setCreateTime = t.getClass().getMethod("setCreateTime", Date.class);
			setCreateTime.invoke(t, new Date());
			if(mapper.insertSelective(t)>0){
				return ResultBody.success("新增成功");
			}else{
				return ResultBody.error("已成功执行语句，没有受影响的行！！！");
			}
	}
	
	/**
	 * 
	 * 通过id 批量删除对象数据
	 * @param t
	 * @param ids
	 * @return
	 */
	@Transactional(propagation= Propagation.REQUIRED,rollbackFor=Exception.class)
	public int removesByIds(Class<T> t, String[] ids) {
		int count = 0;
		if(ids.length>0){
			// TODO 自动生成的方法存根
			Example example=new Example(t);
			Criteria criteria = example.createCriteria();
			criteria.andIn("id", Arrays.asList(ids));
			 count = mapper.deleteByExample(example);
		}
		return count;
	}
	
	
	/**
	 * 根绝主键校验是否存在
	 * @param key
	 * @return
	 */
	public boolean isExists(String key) {
		if(null != mapper && mapper.existsWithPrimaryKey(key)){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 根绝主键校验是否存在
	 * @param assetsId
	 * @return
	 */
	public boolean isExists(T t) {
		if(mapper.selectCount(t)>0){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 添加
	 *
	 * @param lists
	 * @return
	 * @throws Exception
	 */
	@Transactional(propagation= Propagation.REQUIRED,rollbackFor=Exception.class)
	public int adds(List<T> lists) throws Exception{
		int count = 0;
		for (T t : lists) {
			count += mapper.insertSelective(t);  // 保存数据
		}
		return count;
	}
	
}
