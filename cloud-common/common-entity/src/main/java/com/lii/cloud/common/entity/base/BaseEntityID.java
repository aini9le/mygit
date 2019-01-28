package com.lii.cloud.common.entity.base;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 公用属性
 * @author Administrator
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseEntityID implements Serializable{
	
	private static final long serialVersionUID = 4116182769120672569L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
