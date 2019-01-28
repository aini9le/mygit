package com.lii.cloud.upload.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.lii.cloud.common.tools.result.ResultBody;
import com.lii.cloud.common.tools.utils.FileUrlUtil;
import com.lii.cloud.upload.result.FileUploadInfo;
import com.lii.cloud.upload.result.ResponseFileBody;

@Controller
public class FileUploadController {
	@Value("${fileUpload.prefix}")
	private String fileUploadPrefix;  //文件上传路径前缀
	@Value("${fileUpload.prefixUrl}")
	private String filePrefixUrl;  //文件访问路径前缀


	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public String uploadIndex(){
		return "upload";
	}

	/**
	 * 实现文件上传
	 * */
	@RequestMapping(value="/fileUpload",method=RequestMethod.POST)
	@ResponseBody 
	public ResultBody fileUpload(@RequestParam("file") MultipartFile file,String title){
//	public ResultBody fileUpload(StandardServletMultipartResolve req){
		ResultBody body = new ResultBody();

		ResponseFileBody fileBody = new ResponseFileBody();
		
		if(file.isEmpty()){
			body.setStatus(ResultBody.ERROR);
			return body;
		}

		String fileName = file.getOriginalFilename();
		int size = (int) file.getSize();
		System.out.println(fileName + "-->" + size);

		String url = FileUrlUtil.getFileUrl(fileName);
		FileUploadInfo info = new FileUploadInfo();

		File dest = new File(fileUploadPrefix + url);
		if(!dest.getParentFile().exists()){ //判断文件父目录是否存在
			dest.getParentFile().mkdirs();
		}
		try {
			file.transferTo(dest); //保存文件
			info.setFileUrl(url);
			info.setOldFileName(fileName);
			info.setNewFileName(FilenameUtils.getName(url));
			info.setTitle(title);
			body.setStatus(ResultBody.SUCCESS);
			fileBody.setFilePrefixUrl(filePrefixUrl);
			fileBody.setFile(info);
			body.setInfo(fileBody);
		} catch (IllegalStateException e) {
			body.setStatus(ResultBody.ERROR);
			body.setMessage("文件上传失败....");
		} catch (IOException e) {
			body.setStatus(ResultBody.ERROR);
			body.setMessage("文件上传失败....");
		}
		return body;
	}

	/**
	 * 实现多文件上传
	 * */
	/**public @ResponseBody String multifileUpload(@RequestParam("fileName")List<MultipartFile> files) */
	@RequestMapping(value="/multifileUpload",method=RequestMethod.POST) 
	@ResponseBody
	public ResultBody multifileUpload(HttpServletRequest request){
		ResultBody body = new ResultBody();
		List<MultipartFile> files = ((MultipartHttpServletRequest)request).getFiles("fileName");
		
		if(files.isEmpty()){
			body.setStatus(ResultBody.ERROR);
			return body;
		}

		ResponseFileBody fileBody = new ResponseFileBody();
		fileBody.setFilePrefixUrl(filePrefixUrl);
		List<FileUploadInfo> infos = new ArrayList<FileUploadInfo>();
		FileUploadInfo info = null;
		try {
			for(MultipartFile file:files){
				info = new FileUploadInfo();
				String fileName = file.getOriginalFilename();
				int size = (int) file.getSize();
				System.out.println(fileName + "-->" + size);
				String url = FileUrlUtil.getFileUrl(fileName);

				if(file.isEmpty()){
					body.setStatus(ResultBody.ERROR);
				}else{        
					File dest = new File(fileUploadPrefix + "/" + fileName);
					if(!dest.getParentFile().exists()){ //判断文件父目录是否存在
						dest.getParentFile().mkdirs();
					}

					file.transferTo(dest);
					info.setFileUrl(url);
					info.setOldFileName(fileName);
					info.setNewFileName(FilenameUtils.getName(url));
					body.setStatus(ResultBody.SUCCESS);
					fileBody.setFilePrefixUrl(filePrefixUrl);
					infos.add(info);

				}
			}
		}catch (Exception e) {
			body.setStatus(ResultBody.ERROR);
			body.setMessage("文件上传失败........");
		} 
		fileBody.setFiles(infos);
		body.setInfo(fileBody);
		return body;
	}


}
