package com.woniuxy.yogasystem.filter;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@WebFilter(urlPatterns="/*")
public class FileFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		if (!(request instanceof MultipartHttpServletRequest)) {
			chain.doFilter(request, response);
		}else {
			MultipartHttpServletRequest mhsr =
					(MultipartHttpServletRequest)request;
					//获取文件map
					Map<String, MultipartFile>files = mhsr.getFileMap();
					//获取迭代器
					Iterator<String>iterator = files.keySet().iterator();
					//遍历
					while (iterator.hasNext()) {
					String key = (String) iterator.next();
					MultipartFile multipartFile = files.get(key);
					//获取文件名
					String fileName = multipartFile.getOriginalFilename();
					//判断是否是允许的文件
					if (checkType(fileName)) {
					/*//限制文件类型，请求转发到原始请求页面，并携带错误提示信息
					request.setAttribute("error", "文件格式错误");
					request.getRequestDispatcher("file.jsp")
					.forward(request, response);
					flag = false;*/
						chain.doFilter(request, response);
					
					}
					}
		}
		}
		private boolean checkType(String fileName) {
		//规定允许上传的文件后缀名
		String suffix = "jpg,png,jpeg";
		//获取后缀名
		String lastName = fileName.substring(
		fileName.lastIndexOf(".")+1, fileName.length());
		//判断是否包含
		if (suffix.contains(lastName)) {
		return true;
		}
		return false;
		}
	}

