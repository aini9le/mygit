package com.lii.cloud.common.ueditor;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;

import com.baidu.ueditor.ActionEnter;

@WebServlet(urlPatterns="/ueditorServlet")
public class UeditorHttpServlet extends HttpServlet {
	
	@Value("${fileUpload.prefix}")
	private String fileUploadPrefix;  //文件上传路径前缀

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = null;
		try {
			System.out.println("fileUploadPrefix = " + fileUploadPrefix);
			String rootPath = "D:/tools/fileUpload/";
			if(StringUtils.isNotBlank(fileUploadPrefix)){
				rootPath = this.fileUploadPrefix;
				String exec = new ActionEnter(request,rootPath).exec();
				writer = response.getWriter();
				writer.write(exec);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(null != writer){
				writer.flush();
				writer.close();
			}
		}
	}

	
}
