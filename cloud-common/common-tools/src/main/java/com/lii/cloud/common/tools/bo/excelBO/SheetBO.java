package com.lii.cloud.common.tools.bo.excelBO;

import java.io.Serializable;
import java.util.List;

/**
 * Excel 的sheet 对象
 * @author Administrator
 *
 */
public class SheetBO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer sheetIndex;  // sheet 坐标
	
	private List<RowBO> rows;  // 每个sheet 中的所有行数据集合
	
	public List<RowBO> getRows() {
		return rows;
	}
	public void setRows(List<RowBO> rows) {
		this.rows = rows;
	}
	public Integer getSheetIndex() {
		return sheetIndex;
	}
	public void setSheetIndex(Integer sheetIndex) {
		this.sheetIndex = sheetIndex;
	}
	
}
