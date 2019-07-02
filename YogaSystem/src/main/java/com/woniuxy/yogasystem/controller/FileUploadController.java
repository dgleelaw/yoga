package com.woniuxy.yogasystem.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FileUploadController {
	@PostMapping("/uploadwdf")
	@ResponseBody
	public String uploads(@RequestParam(name = "file") MultipartFile picture, HttpServletRequest request)
			throws IllegalStateException, IOException {
		// 获取文件名
		String filename = picture.getOriginalFilename();
		// 获取保存文件的路径
		String path = request.getServletContext().getRealPath("upload");
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
		// 新文件名（应该保存到数据库）
		filename = UUID.randomUUID().toString() + filename;
		// 新路径
		path = path + File.separator + filename;
		file = new File(path);
		// 保存文件
		picture.transferTo(file);
		Map<String, String> map = new HashMap<String, String>();
		return "/upload/" + filename;
	}

	@PostMapping("/upload")
	@ResponseBody
	public ModelAndView upload(@RequestParam(value = "file") MultipartFile picture, HttpServletRequest request)
			throws IllegalStateException, IOException {
		ModelAndView modelAndView = new ModelAndView();
		// 获取文件名 "/upload/"+filename
		String filename = picture.getOriginalFilename();
		// 获取保存文件的路径
		String path = request.getServletContext().getRealPath("upload");
		System.out.println(path);
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
		// 新文件名（应该保存到数据库）
		filename = UUID.randomUUID().toString() + filename;
		// 新路径
		path = path + File.separator + filename;
		System.out.println(path);
		file = new File(path);
		// 保存文件
		picture.transferTo(file);
		Map<String, String> map = new HashMap<String, String>();
		modelAndView.addObject("filename", "/upload/" + filename);
		modelAndView.setViewName("/html/addAdver.html");
		return modelAndView;
	}
}
