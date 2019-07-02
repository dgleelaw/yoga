package com.woniuxy.yogasystem.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.woniuxy.yogasystem.pojo.Coach;
import com.woniuxy.yogasystem.pojo.Order_Form;
import com.woniuxy.yogasystem.pojo.Request_Message;
import com.woniuxy.yogasystem.pojo.Trainee;
import com.woniuxy.yogasystem.pojo.Venues;
import com.woniuxy.yogasystem.service.CoachService;

/*
 * 教练
 */
@Controller
@RequestMapping("/coach")
public class CoachController {
	@Resource
	private CoachService coachService ;
	
	@ResponseBody
	@RequestMapping("/findCoach")
	public Coach findCoach(Integer id){
		
		return coachService.findCoach(id);
	}
	
	@ResponseBody
	@RequestMapping("/findTraineeMes")
	public List<Request_Message> findTraineeMes(Integer uid){
		return null;
	}
	
	//教练签约场馆
	@RequestMapping("/signVenues")
	@ResponseBody
	public String signVenues(HttpSession session,Integer vid){
		String result = "签约失败";
		int cid = (int)session.getAttribute("uid");
		result = coachService.signVenues(cid, vid);
		return result;
	}
	
	//教练查询申请信息
	@RequestMapping("/findResMessage")
	@ResponseBody
	public Map<String, Object> findResMessage(HttpSession session){
		Map<String , Object> map = new HashMap<>();
		int role = (int)session.getAttribute("role");
		int uid = (int)session.getAttribute("uid");
		List<Request_Message> mes = coachService.findResMessage(uid);	
		map.put("mes", mes);
		map.put("role", role);
		return map;
	}
	
	@RequestMapping("/adopt")
	@ResponseBody
	//教练接受学员签约
	public String adopt(Order_Form order_Form,Integer uid1,Integer uid2,Integer mid,Integer pid){
		String result="处理失败";
		result = coachService.adopt(order_Form, mid,uid1,uid2,pid);	
		return result;
	}
	
	//教练拒绝学员签约
	@RequestMapping("/refuse")
	@ResponseBody
	public String refuse(HttpSession session,int tid, int mid){
		int cid = (int)session.getAttribute("uid");
		String result = "处理失败";
		result = coachService.refuse(cid, tid, mid);
		return result;
	}
	
	//展示教练的收到的提示消息
	@RequestMapping("/findHintMessage")
	@ResponseBody
	public List<Request_Message> findHintMessage(HttpSession session){
		int uid = (int)session.getAttribute("uid");
		return coachService.findHintMessage(uid);
	}
	
	//场馆查看
	@RequestMapping("/findVenues")
	@ResponseBody
	public List<Venues> findVenues(Venues venues){
		return coachService.findVenues(venues);
	}
	
	//查看我的场馆
	@RequestMapping("/findMyVenues")
	@ResponseBody	
	public List<Venues> findVenues(HttpSession session){
		int uid = (int)session.getAttribute("uid");
		return coachService.findMyVenues(uid);
	}
	//查看我的学员
	@RequestMapping("/findMyTrainee")
	@ResponseBody	
	public List<Trainee> findTrainee(HttpSession session){
		int uid = (int)session.getAttribute("uid");
		return coachService.findTrainee(uid);
	}
	
	//删除消息
	@RequestMapping("/removeMes")
	@ResponseBody
	public String removeMes(Integer id){
		return coachService.removeMes(id);
	}
	
	//教练接受场馆请求
	@RequestMapping("/adoptVenues")
	@ResponseBody
	public String adoptVenues(int uid,int cid,int mid){
		
		return null;
	}
	
	//查询角色
	@RequestMapping("/findRole")
	@ResponseBody
	public int findRole(HttpSession session){
		int role = (int)session.getAttribute("role");
		
		return role;
	}
}
