package com.lii.cloud.common.entity.base;

import java.util.Date;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 公用属性
 * @author Administrator
 *
 */
public class BaseEntityIdAndDateEnable extends BaseEntityIdAndDate{
	
	private static final long serialVersionUID = 4116182769120672569L;
	/**
     * 是否启用  Y：启用   N:不启用
     */
    @Column(name = "is_enable")
    private String isEnable;

    /**
     * 启用/暂停 时间
     */
    @Column(name = "enable_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date enableDate;

    /**
     * 获取是否启用  Y：启用   N:不启用
     *
     * @return is_enable - 是否启用  Y：启用   N:不启用
     */
    public String getIsEnable() {
        return isEnable;
    }

    /**
     * 设置是否启用  Y：启用   N:不启用
     *
     * @param isEnable 是否启用  Y：启用   N:不启用
     */
    public void setIsEnable(String isEnable) {
        this.isEnable = isEnable;
    }

    /**
     * 获取启用/暂停 时间
     *
     * @return enable_date - 启用/暂停 时间
     */
    public Date getEnableDate() {
        return enableDate;
    }

    /**
     * 设置启用/暂停 时间
     *
     * @param enableDate 启用/暂停 时间
     */
    public void setEnableDate(Date enableDate) {
        this.enableDate = enableDate;
    }
  
}
