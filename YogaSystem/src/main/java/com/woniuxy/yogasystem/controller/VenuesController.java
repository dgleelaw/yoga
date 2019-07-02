package com.woniuxy.yogasystem.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.woniuxy.yogasystem.pojo.Coach;
import com.woniuxy.yogasystem.pojo.Request_Message;
import com.woniuxy.yogasystem.pojo.Trainee;
import com.woniuxy.yogasystem.pojo.Venues;
import com.woniuxy.yogasystem.service.VenuesService;

@Controller
@RequestMapping("/venues")
public class VenuesController {
	@Resource
	private VenuesService venuesService;

	@RequestMapping("/find")
	public String findVenues(ModelMap map, int id) {
		Venues venues = venuesService.findVenues(id);
		map.put("po", venues);
		return "/html/venues.html";
	}

	// 搜索学员功能
	@RequestMapping("/findManyTraineeMsg")
	@ResponseBody
	public List<Trainee> findManyTraineeMsg(Trainee trainee) {
		return venuesService.findManyTraineeMsg(trainee);
	}

	// 搜索教练功能
	@RequestMapping("/findManyCoachMsg")
	@ResponseBody
	public List<Coach> findManyCoachMsg(Coach coach) {
		return venuesService.findManyCoachMsg(coach);
	}

	// 查看我的签约学员
	@RequestMapping("/findSignTraineeMsg")
	@ResponseBody
	public List<Trainee> findSignTraineeMsg(HttpSession session) {
		int uid = (int) session.getAttribute("uid");
		return venuesService.findSignTraineeMsg(uid);
	}

	// 查看我签约的教练
	@RequestMapping("/findSignCoachMsg")
	@ResponseBody
	public List<Coach> findSignCoachMsg(HttpSession session) {
		int uid = (int) session.getAttribute("uid");
		return venuesService.findSignCoachMsg(uid);
	}

	// 签约教练
	@RequestMapping("/appointCoach")
	@ResponseBody
	public String appointCoach(Venues venues, int uid2, int price) {
		String sendCoachMsg = venuesService.sendCoachMsg(venues, uid2, price);
		return "申请消息已发送";
	}

	// 展示通知消息
	@RequestMapping("/showAcceptMsg")
	@ResponseBody
	public List<Request_Message> showAcceptMsg(int uid) {
		return venuesService.findMsgContent(uid);
	}

	// 场馆接受教练通知消息
	@RequestMapping("/acceptCoachMsg")
	@ResponseBody
	public String acceptCoachMsg(int uid1, int uid2, int mid) {
		String msg = venuesService.acceptCoachMsg(uid2, uid1, mid);
		return msg;
	}

	@RequestMapping("/acceptTrainMsg")
	@ResponseBody
	// 场馆接受学员通知的消息
	public String acceptTrainMsg(int uid1, int uid2, int mid) {
		String msg = venuesService.acceptTrainMsg(uid1, uid2, mid);
		return msg;
	}

	// 场馆拒绝教练或者学员的消息
	@RequestMapping("/refuseAllMsg")
	@ResponseBody
	public String refuseAllMsg(int vid, int cid, int id) {
		return "拒绝成功";
	}

	
	@RequestMapping("/fingImg")
	@ResponseBody
	public ModelAndView findImgById(int uid){
		ModelAndView mv=new ModelAndView();
		List<String> img=new ArrayList<>();
		img=venuesService.findImgById(uid);
		mv.addObject("img", img);
		mv.setViewName("html/venuesImage.html");
		return mv;
	}
}
