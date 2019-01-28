package com.lii.cloud.upload.result;

import java.util.List;

import com.lii.cloud.common.tools.result.ResponseInfo;

/**
 * 文件上传返回的集合信息
 * @author Administrator
 *
 */
public class ResponseFileBody implements ResponseInfo {
	
	private String filePrefixUrl;  // 文件访问路径前缀
	private List<FileUploadInfo> files;  //文件对象集合
	private FileUploadInfo file;  //文件对象集合

	public List<FileUploadInfo> getFiles() {
		return files;
	}

	public void setFiles(List<FileUploadInfo> files) {
		this.files = files;
	}

	public String getFilePrefixUrl() {
		return filePrefixUrl;
	}

	public void setFilePrefixUrl(String filePrefixUrl) {
		this.filePrefixUrl = filePrefixUrl;
	}

	public FileUploadInfo getFile() {
		return file;
	}

	public void setFile(FileUploadInfo file) {
		this.file = file;
	}
	
}
