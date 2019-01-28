package com.lii.cloud.common.entity.base;

import java.util.Date;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 公用属性
 * @author Administrator
 *
 */
public class BaseEntityIdAndDate extends BaseEntityID{
	
	private static final long serialVersionUID = 4116182769120672569L;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createDate;

    /**
     * 获取创建时间
     *
     * @return create_date - 创建时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置创建时间
     *
     * @param createDate 创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
