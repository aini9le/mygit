package com.lii.cloud.common.tools.bo.excelBO;

import java.io.Serializable;

/**
 * 每个单元格数据属性
 * @author Administrator
 *
 */
public class CellBO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer cellIndex; //单元格列坐标
	
	private Object value; // 单元格数据

	public Integer getCellIndex() {
		return cellIndex;
	}

	public void setCellIndex(Integer cellIndex) {
		this.cellIndex = cellIndex;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
	
}
