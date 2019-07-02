package com.woniuxy.yogasystem.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.woniuxy.yogasystem.pojo.View;
import com.woniuxy.yogasystem.service.ViewService;

@Controller
@RequestMapping("/view")
public class ViewController {
	@Autowired
	private ViewService viewService;
	@RequestMapping("/insertView")
	
	public String insertView(int id,String text,HttpSession session){
		
			View view=new View();
			SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yy:MM:dd-hh:mm:ss");
			String name=(String) session.getAttribute("name");
			String img=(String) session.getAttribute("headimg");
			view.setCid(id);
			view.setText(text);
		    view.setImg(img);
			view.setName(name);
			view.setTime(simpleDateFormat.format(new Date()));
			viewService.insertView(view);
			return "redirect:/html/profile.html";
		
		
	}
	@RequestMapping("/insertOtherView")
	
	public ModelAndView insertOtherView(ModelAndView modelAndView,String uid,String img,String name,int id,String text,HttpSession session,HttpServletRequest request,HttpServletResponse response) throws Exception{
		
			View view=new View();
			SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yy:MM:dd-hh:mm:ss");
			String uname=(String) session.getAttribute("name");
			String uimg=(String) session.getAttribute("headimg");
			view.setCid(id);
			view.setText(text);
		    view.setImg(uimg);
			view.setName(uname);
			view.setTime(simpleDateFormat.format(new Date()));
			viewService.insertView(view);
			modelAndView.setViewName("redirect:/user/otherInfo?uid="+uid+"&img="+img+"&name="+name+"");
			return modelAndView;
		
		
	}
}
