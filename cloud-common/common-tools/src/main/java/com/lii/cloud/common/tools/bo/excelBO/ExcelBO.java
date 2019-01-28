package com.lii.cloud.common.tools.bo.excelBO;

import java.io.Serializable;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

/**
 * 读取excel 产生的bo对象
 * @author Administrator
 *
 */
public class ExcelBO implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<SheetBO> sheets;  //工作薄   集合
	private Workbook wb; 

	public List<SheetBO> getSheets() {
		return sheets;
	}

	public void setSheets(List<SheetBO> sheets) {
		this.sheets = sheets;
	}

	public Workbook getWb() {
		return wb;
	}

	public void setWb(Workbook wb) {
		this.wb = wb;
	}
	
}
