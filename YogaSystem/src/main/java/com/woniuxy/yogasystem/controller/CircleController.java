package com.woniuxy.yogasystem.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.woniuxy.yogasystem.pojo.Circle_of_Friend;
import com.woniuxy.yogasystem.service.CircleService;

import ch.qos.logback.core.net.SyslogOutputStream;

@Controller
@RequestMapping("/circle")
public class CircleController {
	@Resource
	private CircleService circleService;

	@RequestMapping("/insertCircle") // 新增朋友圈
	@ResponseBody
	public String insertCircle(Circle_of_Friend circle, HttpSession session) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd:hh:mm:ss");
		try {
			// 用户id
			
			 int uid= (int) session.getAttribute("uid");
			 // 用户头像
			String name=(String) session.getAttribute("name");
			String img=(String) session.getAttribute("headimg");
			circle.setHead(img);
			circle.setUid(uid);
			circle.setName(name);
			circle.setTime(simpleDateFormat.format(new Date()));
			System.out.println(circle);
			circleService.insertCircle(circle);
			return "发送成功";
		} catch (Exception e) {
			e.printStackTrace();
			return "发送失败联系管理员";
		}

	}

	// 找到关注人的朋友圈
	@RequestMapping("/findAllCircle")
	@ResponseBody
	public Map<String, Object> findAllCircle(Integer nowpage, HttpSession session) {
		int uid=(int) session.getAttribute("uid");
		int all = circleService.findAllCircle(uid).size();
		int totalPage = all / 10;
		if ((all % 10) != 0) {
			totalPage++;
		}
		int start = nowpage * 10;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("totalPage", totalPage);
		map.put("data", circleService.findTenCircle(uid, start));
		System.out.println(map.get("data"));
		return map;
	}

	// 找到自己的朋友圈CX
	@RequestMapping("/findMyTenCircle")
	@ResponseBody
	public Map<String, Object> findMyCircle(int uid, Integer nowpage) {
		int all = circleService.findMyCircle(uid).size();
		int totalPage = all / 10;
		if ((all % 10) != 0) {
			totalPage++;
		}
		int start = nowpage * 10;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("totalPage", totalPage);
		map.put("data", circleService.findMyTenCircle(uid, start));
		return map;
	}

	// 删除指定的朋友圈
	@RequestMapping("/deleteCircle")
	@ResponseBody
	public String deleteCircle(int id) {
		try {
			circleService.deleteAd(id);
			return "删除成功";
		} catch (Exception e) {
			return "删除失败,联系管理员";
		}
	}
}
