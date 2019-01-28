package com.lii.cloud.common.base.tools;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * Example 方式 查询单个 实体对象 的数据封装
 * @author Administrator
 *
 */
public class ExampleQueryUtil {
	
	/**
	 * 封装查询参数
	 * @param cr
	 * @param key
	 * @param obj
	 */
	public static void exampleQuery(Criteria cr,String key,Object obj){
		if(null != cr && StringUtils.isNotEmpty(key) && null != obj && StringUtils.isNotEmpty(obj.toString())){
			String [] str = key.trim().split("_");
			String k = str[0];
			String value = obj.toString().trim();
			if(str.length == 1)
				cr.andEqualTo(key, value);
			else{
				if("eq".equals(str[1]))
					cr.andEqualTo(k, value);
				if("neq".equals(str[1]))   //不等于
					cr.andNotEqualTo(k, value);
				if("in".equals(str[1])){    //
					List<String> list = Arrays.asList(StringUtils.split(value,","));
					cr.andIn(k, list);
				}
				if("nin".equals(str[1])){  // 
					List<String> list = Arrays.asList(StringUtils.split(value,","));
					cr.andNotIn(k, list);
				}
				if("null".equals(str[1]))  //为空
					cr.andIsNull(k);
				if("nnull".equals(str[1]))  //不为空
					cr.andIsNotNull(k);
				if("ge".equals(str[1]))  //大于
					cr.andGreaterThan(k, value);
				if("geq".equals(str[1]))//大于等于
					cr.andGreaterThanOrEqualTo(k, value);
				if("le".equals(str[1]))    // 小于
					cr.andLessThan(k, value);
				if("leq".equals(str[1]))    //小于等于
					cr.andLessThanOrEqualTo(k, value);
				if("bet".equals(str[1])){   // 范围内取值
					String [] value1 = value.split(",");
					cr.andBetween(k, value1[0], value1[1]);
				}
				if("nbet".equals(str[1])){   //  不在范围内取值
					String [] value1 = value.split(",");
					cr.andNotBetween(k, value1[0], value1[1]);
				}
				if("like".equals(str[1]))
					cr.andLike(k, "%"+value+"%");
				if("nlike".equals(str[1]))
					cr.andNotLike(k, "%"+value+"%");
			}
		}
	}

}
