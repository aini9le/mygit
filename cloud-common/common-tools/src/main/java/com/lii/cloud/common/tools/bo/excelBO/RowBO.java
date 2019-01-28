package com.lii.cloud.common.tools.bo.excelBO;

import java.io.Serializable;
import java.util.List;

/**
 * 行数据BO对象
 * @author Administrator
 */
public class RowBO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer rowIndex;  // 行坐标
	
	private List<CellBO> cells;  //单元格集合

	public Integer getRowIndex() {
		return rowIndex;
	}

	public void setRowIndex(Integer rowIndex) {
		this.rowIndex = rowIndex;
	}

	public List<CellBO> getCells() {
		return cells;
	}

	public void setCells(List<CellBO> cells) {
		this.cells = cells;
	}
	
}
