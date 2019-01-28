package com.lii.cloud.upload.result;

/**
 * 文件上传后 返回的信息封装
 * @author Administrator
 *
 */
public class FileUploadInfo {
	
	private String oldFileName;  // 原文件名称
	private String newFileName;  //上传之后的文件名称
	private String fileUrl;      //文件上传后的相对路径
	private String title;//标题
	
	public String getOldFileName() {
		return oldFileName;
	}
	public void setOldFileName(String oldFileName) {
		this.oldFileName = oldFileName;
	}
	public String getNewFileName() {
		return newFileName;
	}
	public void setNewFileName(String newFileName) {
		this.newFileName = newFileName;
	}
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
}
