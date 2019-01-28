package com.lii.cloud.common.tools.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.lii.cloud.common.tools.bo.excelBO.CellBO;
import com.lii.cloud.common.tools.bo.excelBO.ExcelBO;
import com.lii.cloud.common.tools.bo.excelBO.RowBO;
import com.lii.cloud.common.tools.bo.excelBO.SheetBO;

public class ExcelUtils {
	
	public static void main(String[] args) {
//        File file = new File("D:\\tools\\test\\importProject.xlsx");	
		String url = "E:\\tools\\工作文档\\Hr接口\\接口\\日记账录入\\日记账录入\\Payroll Costing Report.xlsx";
		System.out.println(FilenameUtils.getExtension(url));
		File files = new File(url);
		ExcelBO excelBo = analysisWorkbook(files);
		print(excelBo);
	}
	
	/**
	 * 将错误信息写入Excel
	 * @param files
	 * @param message
	 * @return
	 */
	public static Workbook getWorkbook(File files,String message){
		Workbook wb = null;
		try {
			//将临时文件夹中的文件读取到内存中
			FileInputStream fis = new FileInputStream(files);
			wb = new XSSFWorkbook(fis);
			Sheet sheet=wb.getSheetAt(0);
			Row row = sheet.getRow(0);
			Cell cell = row.createCell(0);
			cell.setCellValue(message);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return wb;
	}
	
	/**
	 * 通过输入流获取数据对象
	 * @param type 默认.xlsx格式
	 * @param inputStream
	 * @return
	 */
	public static ExcelBO analysisWorkbookInputStream(String type,InputStream inputStream){
		ExcelBO excelBo = new ExcelBO();
		if(StringUtils.isBlank(type))
			type = ".xlsx";
		try {
			getExcelBoByInputStream(type, excelBo, inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return excelBo;
	}
	
	/**
	 * 将Excel 文件转换为ExcelBO对象
	 * @param files
	 * @return
	 */
	public static ExcelBO analysisWorkbook(File files){
		ExcelBO excelBo = new ExcelBO();
        try {
        	String url = files.getPath();
    		String suffix = url.substring(url.lastIndexOf("."));
        	//将临时文件夹中的文件读取到内存中
			FileInputStream fis = new FileInputStream(files);
			getExcelBoByInputStream(suffix, excelBo, fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return excelBo;
	}

	/**
	 * 将excel数据流转换为 ExcelBO 实体对象
	 * @param type
	 * @param excelBo
	 * @param fis
	 * @throws IOException
	 */
	protected static void getExcelBoByInputStream(String type, ExcelBO excelBo, InputStream fis) throws IOException {
		Workbook wb = null;
		Sheet sheet = null;
		Cell cell = null;
		Row row = null;
		if(".xlsx".equals(type)){
			wb = new XSSFWorkbook(fis);
		}
		if(".xls".equals(type)){
			wb=new HSSFWorkbook(fis);
		}
		List<SheetBO> sheetBos = new ArrayList<SheetBO>();
		
		SheetBO sheetBo = null;
		RowBO rowBo = null;
		CellBO cellBo = null;
		List<CellBO> cellBos = null;
		List<RowBO> rowBos = null;
		//第一行为表头不需要,从第二行开始取数据 i=1
		int sheetTotal = wb.getNumberOfSheets();
		
		for(int s=0;s<sheetTotal;s++){
			sheet=wb.getSheetAt(s);
			int total = sheet.getLastRowNum();
//				sheetBos = new ArrayList<SheetBO>();
//				RowBO rowBo = null;
			sheetBo = new SheetBO();
			sheetBo.setSheetIndex(s);
			rowBos = new ArrayList<RowBO>();
			for(int i=1;i<=total;i++){
				row = sheet.getRow(i);
				if(null == row){
					break;
				}
				rowBo = new RowBO();
				rowBo.setRowIndex(i);
				cellBos = new ArrayList<CellBO>();
		    	for (int j = 0; j < row.getLastCellNum(); j++) {  
		    		cellBo = new CellBO();
		    		cellBo.setCellIndex(j);
		            // 得到一行中的单元格  
		            cell = row.getCell(j);  
		            if(null != cell){
//	                    	cell.setCellType(Cell.CELL_TYPE_STRING);
		                cellBo.setValue(getCellByType(cell)); 
		            }
		            cellBos.add(cellBo);
		        }  
		    	rowBo.setCells(cellBos);
		    	rowBos.add(rowBo);
				sheetBo.setRows(rowBos);
		    }
			sheetBos.add(sheetBo);
		}
		excelBo.setWb(wb);
		excelBo.setSheets(sheetBos);
	}
	
	/**
	 * 判断cell类型并取值
	 * @param cell
	 * @return
	 */
	private static Object getCellByType(Cell cell){
		Object value = null;
		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_NUMERIC: // 数字
			//如果为时间格式的内容
			if (HSSFDateUtil.isCellDateFormatted(cell)) {      
				//注：format格式 yyyy-MM-dd hh:mm:ss 中小时为12小时制，若要24小时制，则把小h变为H即可，yyyy-MM-dd HH:mm:ss
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
				value=sdf.format(HSSFDateUtil.getJavaDate(cell.
						getNumericCellValue())).toString();                                 
				break;
			} else {
				String str = String.valueOf(cell.getNumericCellValue());
				//截取最后两位
				String s = str.substring(str.length()-2, str.length());
				if(s.equals(".0")){
					str = str.replace(".0", "");
				}
				value = str;
			}
			break;
		case HSSFCell.CELL_TYPE_STRING: // 字符串
			value = cell.getStringCellValue();
			break;
		case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
			value = cell.getBooleanCellValue() + "";
			break;
		case HSSFCell.CELL_TYPE_FORMULA: // 公式
			value = cell.getCellFormula() + "";
			break;
		case HSSFCell.CELL_TYPE_BLANK: // 空值
			value = "";
			break;
		case HSSFCell.CELL_TYPE_ERROR: // 故障
			break;
		default:
			break;
		}
		return value;
	}
	
	private static void print(ExcelBO excelBo){
		
		if(null != excelBo){
			List<SheetBO> sheetBo = excelBo.getSheets();
			for (SheetBO sheet : sheetBo) {
				System.out.println("sheet 坐标 = "+ sheet.getSheetIndex());
				List<RowBO> rows = sheet.getRows();
				for (RowBO row : rows) {
					System.out.println("行坐标="+row.getRowIndex());
					List<CellBO> cells = row.getCells();
					for (CellBO cell : cells) {
						System.out.print("坐标="+cell.getCellIndex() +"     值"+cell.getValue()+"       ");
					}
					System.out.println();
				}
			}
		}
		
	}

}
