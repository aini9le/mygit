package com.lii.cloud.admin.services;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.lii.cloud.admin.dto.LogPageInfoDTO;
import com.lii.cloud.admin.tools.UIPageInfo;
import com.lii.cloud.common.base.core.mongo.ExceptionEntity;

@Service
public class LogService {
	
	@Autowired
	private MongoTemplate template;

	/**
	 * 获取日志信息
	 * @param pageDto
	 * @return
	 */
	public UIPageInfo getLog(LogPageInfoDTO pageDto){
		Query query = new Query();
		Criteria criteria = null;
		if(StringUtils.isNotBlank(pageDto.getIp())){
			criteria = Criteria.where("ip").is(pageDto.getIp());
		}
		if(StringUtils.isNotBlank(pageDto.getPath())){
            if(null != criteria){
            	criteria.and("path").is(pageDto.getPath());
            }else{
            	criteria = Criteria.where("path").is(pageDto.getPath());
            }
		}
		if(null != criteria){
			query.addCriteria(criteria);
		}
		query.with(new Sort(new Sort.Order(Sort.Direction.DESC, "create")));
		query.skip(pageDto.getPageSize() * (pageDto.getPageNumber()-1));
		query.limit(pageDto.getPageSize());
		long count = template.count(query, ExceptionEntity.class);
		List<ExceptionEntity> find = template.find(query, ExceptionEntity.class);
		UIPageInfo ui = new UIPageInfo();
		ui.setTotal(count);
		ui.setRows(find);
		return ui;
	}
	
	/**
	 * 删除日志信息
	 * @param info
	 */
	public void remove(LogPageInfoDTO info){
		if(null != info && StringUtils.isNotBlank(info.getWhere())){
			if("ALL".equals(info.getWhere())){
				template.dropCollection(ExceptionEntity.class);
			}else{
				Query query = new Query();
				LocalDate d = LocalDate.now(); 
				LocalDate days = d.minusDays(Integer.parseInt(info.getWhere()));
				LocalDate.parse(days.toString("yyyy-MM-dd"));
				LocalDate newDay = LocalDate.parse(days.toString("yyyy-MM-dd"));
				Criteria criteria = Criteria.where("create").lt(newDay.toDate());
				query.addCriteria(criteria);
				template.remove(query, ExceptionEntity.class);
			}
		}
	}
}
