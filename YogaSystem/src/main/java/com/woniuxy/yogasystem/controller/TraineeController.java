package com.woniuxy.yogasystem.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.woniuxy.yogasystem.pojo.Coach;
import com.woniuxy.yogasystem.pojo.Order_Form;
import com.woniuxy.yogasystem.pojo.Private_Course;
import com.woniuxy.yogasystem.pojo.Request_Message;
import com.woniuxy.yogasystem.pojo.Trainee;
import com.woniuxy.yogasystem.pojo.Venues;
import com.woniuxy.yogasystem.service.TraineeService;

@Controller
@RequestMapping("/trainee")
public class TraineeController {

	@Resource
	private TraineeService traineeService;

	@RequestMapping("/find")
	public String findTrainee(ModelMap map, int id) {
		Trainee trainee = traineeService.findTrainee(id);
		map.put("po", trainee);
		return "/venues.html";
	}

	// 查看教练的基本信息
	@PostMapping("/findCoachBaiscMsg")
	@ResponseBody
	public Coach findCoachBaiscMsg(int uid) {
		return traineeService.findCoachBaiscMsg(uid);
	}

	// 查看教练的详细信息
	@GetMapping("/findCoachDetailMsg")
	public String findCoachDetailMsg(int uid, ModelMap map) {
		Coach coach = traineeService.findCoachDetailMsg(uid);
		map.put("coach", coach);
		return "/html/coach.html";
	}

	// 查看场馆的基本信息
	@PostMapping("/findVenuesBaiscMsg")
	@ResponseBody
	public Venues findVenuesBaiscMsg(int uid) {
		return traineeService.findVenuesBaiscMsg(uid);
	}

	// 查看场馆的详细信息
	@PostMapping("/findVenuesDetailMsg")
	@ResponseBody
	public Venues findVenuesDetailMsg(int uid) {
		Venues findVenuesDetailMsg = traineeService.findVenuesDetailMsg(uid);
		return findVenuesDetailMsg;
	}

	// 搜索教练功能==图文展示
	// 根据条件教练的详细信息=条件检索：姓名，流派，认证，id
	@GetMapping("/findCoachMsg")
	public String findCoachMsg(Coach coach, ModelMap map) {
		List<Coach> coachs = traineeService.findCoachMsg(coach);
		map.put("coachs", coachs);
		return "/html/findcoach.html";
	}

	// 搜索场馆功能
	// 根据条件场馆的详细信息=条件检索：姓名，地址
	@PostMapping("/findVenues")
	@ResponseBody
	public List<Venues> findVenues(Venues venues) {
		return traineeService.findVenues(venues);
	}

	// 查询我的教练信息
	@PostMapping("/findMyCoachMsg")
	@ResponseBody
	public List<Coach> findMyCoachMsg(HttpSession session) {
		int uid = (int)session.getAttribute("uid");
		List<Coach> coachs = traineeService.findMyCoachMsg(uid);
		return coachs;
	}

	// 查看我的场馆
	@PostMapping("/findMyVenuesMsg")
	@ResponseBody
	public List<Venues> findMyVenuesMsg(HttpSession session) {
		int uid = (int)session.getAttribute("uid");
		List<Venues> venues = traineeService.findMyVenuesMsg(uid);
		return venues;
	}

	// 约私教
	// url:/trainee//appointCoach
	// 传递的参数：发送邀请者uid1，type=0（0申请，1代表消息）,学员余额balanc
	// 学员的姓名name，学员的头像，角色character=0,0代表学员
	// 被邀请者（教练）uid2，教练私教课程pid，教练price，
	@RequestMapping("/appointCoach")
	@ResponseBody
	public String appointCoach(HttpSession session,Trainee trainee, int uid2, int pid, int price, int vid) {
		int uid = (int)session.getAttribute("uid");
		trainee.setUid(uid);
		return traineeService.sendCoachMsg(trainee, uid2, pid, price, vid);
	}

	@RequestMapping("/appointVenues")
	@ResponseBody
	// 约场馆
	public String appointVenues(HttpSession session,int uid2, int price) {
		int uid1 = (int)session.getAttribute("uid");
		return traineeService.sendVenuesMsg(uid1, uid2, price);
	}

	// 查看通知消息
	@RequestMapping("/showAcceptMsg")
	@ResponseBody
	public List<Request_Message> showAcceptMsg(int uid) {
		return traineeService.findMsgContent(uid);
	}

	// 查看我的订单
	public List<Order_Form> showOrderMsg(int uid) {
		return traineeService.findTraineeOrder(uid);
	}

	// 查看教练的课程
	@GetMapping("/findCoachCourse")
	public String findCoachCourse(int uid, ModelMap map) {
		List<Private_Course> courses = traineeService.findCoachCourse(uid);
		map.put("courses", courses);
		return "/html/coachcourse.html";
	}

}
