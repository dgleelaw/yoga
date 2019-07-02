package com.woniuxy.yogasystem.filter;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class FileTypeInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		boolean flag = true;
		// 判断是否为文件上传请求
		if (request instanceof MultipartHttpServletRequest) {
			MultipartHttpServletRequest mhsr = (MultipartHttpServletRequest) request;
			// 获取文件map
			Map<String, MultipartFile> files = mhsr.getFileMap();
			// 获取迭代器
			Iterator<String> iterator = files.keySet().iterator();
			// 遍历
			while (iterator.hasNext()) {
				String key = (String) iterator.next();
				MultipartFile multipartFile = files.get(key);
				// 获取文件名
				String fileName = multipartFile.getOriginalFilename();
				// 判断是否是允许的文件
				if (!checkType(fileName)) {
					flag = false;
				}
			}
		}
		return flag;
	}

	private boolean checkType(String fileName) {
		// 规定允许上传的文件后缀名
		String suffix = "jpg,png,jpeg";
		// 获取后缀名
		String lastName = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
		// 判断是否包含
		if (suffix.contains(lastName)) {
			return true;
		}
		return false;
	}
}
