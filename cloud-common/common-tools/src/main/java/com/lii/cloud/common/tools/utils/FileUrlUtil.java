package com.lii.cloud.common.tools.utils;

import java.util.Date;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

public class FileUrlUtil {
	
	/**
	 * 根据文件名  重新生成 文件的上传路径
	 * @param filename
	 * @return
	 */
	public static String getFileUrl(String filename){
		String url = "/";
		if(StringUtils.isNotBlank(filename)){
			url +=DateUtil.FormatDate(new Date(),DateUtil.DATESTYLE_YEAR_MONTH)+"/";
			url +=DateUtil.getCurrDate()+"/";
			url += System.currentTimeMillis()+"."+FilenameUtils.getExtension(filename);
		}
		return url;
	}

}
