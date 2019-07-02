package com.woniuxy.yogasystem.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.woniuxy.yogasystem.pojo.Address;
import com.woniuxy.yogasystem.pojo.Coach;
import com.woniuxy.yogasystem.pojo.Follows;
import com.woniuxy.yogasystem.pojo.Trainee;
import com.woniuxy.yogasystem.pojo.User;
import com.woniuxy.yogasystem.pojo.Venues;
import com.woniuxy.yogasystem.service.AddressService;
import com.woniuxy.yogasystem.service.FollowsService;
import com.woniuxy.yogasystem.service.UserService;
import com.woniuxy.yogasystem.service.VenuesService;

@Controller
@RequestMapping("/follows")
public class FollowsController {
	@Resource
	private FollowsService followsService;
	@Resource
	private UserService userService;
	@Resource
	private AddressService addressService;
	@Resource
	private VenuesService venuesService;

	// 找到我互相关注的人
	@RequestMapping("/findAllFollowerByUid")
	@ResponseBody
	public List<Follows> findAllFollowerByUid(HttpSession session) {
		int uid = (int) session.getAttribute("uid");
		return followsService.FindAllFollowsUidByOwnerUid(uid);

	}

	// 找到我关注的人
	@RequestMapping("/findAllFollows")
	@ResponseBody
	public List<Follows> findAllFollows(HttpSession session) {
		int uid = (int) session.getAttribute("uid");
		return followsService.findAllFollows(uid);

	}// 找到ta关注的人
	@RequestMapping("/findAllOtherFollows")
	@ResponseBody
	public List<Follows> findAllOtherFollows(int uid) {
	
		return followsService.findAllFollows(uid);

	}

	// 找到我的关注者
	@RequestMapping("/findMyFollows")
	@ResponseBody
	public List<Follows> findMyFollows(HttpSession session) {
		int uid = (int) session.getAttribute("uid");
		return followsService.findMyFollows(uid);

	}
	// 找到ta的关注者
		@RequestMapping("/findMyOtherFollows")
		@ResponseBody
		public List<Follows> findMyOtherFollows(int uid) {
			/*int uid = (int) session.getAttribute("uid");*/
			return followsService.findMyFollows(uid);

		}

	// 找到我的信息
	@RequestMapping("/findInfo")
	@ResponseBody
	public Map<String, String> findInfo(HttpSession session) {
		
		Map<String, String> map = new HashMap<>();
		String img = (String) session.getAttribute("headimg");
		String name = (String) session.getAttribute("name");
		map.put("img", img);
		map.put("name", name);
		return map;

	}
	//删除
	@RequestMapping("/deleteFollows")
	public String deleteFollows(int followuid,HttpSession session){
		Follows follows=new Follows();
		int uid = (int) session.getAttribute("uid");
		follows.setUid(uid);
		follows.setFollowuid(followuid);
		followsService.deleteFollows(follows);
		return "redirect:/html/profile.html";
	}
	@RequestMapping("/insertFollows")
	public String insertFollows(int followuid,HttpSession session) {
		try {
			String myname=(String) session.getAttribute("name");
			String myimg=(String) session.getAttribute("headimg");
			int uid=(int) session.getAttribute("uid");
			Map<String, Object>map=(Map<String, Object>) userService.findAllInfoByUid(followuid);
			User user= (User) map.get("user");
			Object object= map.get("object");
			Follows follows=new Follows();
			follows.setFollowuid(followuid);
			follows.setMyimg(myimg);
			follows.setMyname(myname);
			follows.setCharacter(user.getRole());
			follows.setUid(uid);
			if ( object instanceof Trainee) {
				Trainee trainee=(Trainee) object;
				follows.setName(trainee.getName());
				follows.setStatus(trainee.getStatus());
				follows.setImg(trainee.getImg());
			}else if(object instanceof Coach){
				Coach coach=(Coach) object;
				follows.setName(coach.getName());
				follows.setStatus(coach.getInfostatus());
				follows.setImg(coach.getImg());
			}else {
				Venues venues=(Venues) object;
				follows.setName(venues.getName());
				follows.setStatus(2);
				follows.setImg(venues.getImg());
			}
			followsService.insertFollows(follows);
			return "redirect:/html/profile.html";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "关注失败";
		}

	}
	@RequestMapping("/findFollowid")
	@ResponseBody
	public Map<String, Object> findFollowByUid(int otherUid, int uid) {
		// 信息是否公开 0保密，1好友公开，2全部公开
		int status = 0;
		Object obj = null;
		// 0 不是好友，1是好友
		int relation = 0;
		// 被查询人的个人信息Map
		Map<String, Object> map1 = new HashMap<>();
		Map<String, Object> map = new HashMap<>();

		// 查询是否是好友关系
		relation = followsService.findFollowByUid(uid, otherUid);
		// 查询个人信息
		map1 = (Map<String, Object>) userService.findAllInfoByUid(otherUid);
		// 查询地址
		Address address = new Address();
		address = addressService.findAddressById(otherUid);
		map.put("address", address);

		// 查询信息是否公开 0保密，1好友公开，2全部公开
		obj = map1.get("object");
		// 查询回来的类分角色
		// 学员
		if (obj instanceof Trainee) {
			Trainee trainee = (Trainee) obj;
			status = trainee.getStatus();
			if (status == 2 || (status == 1 && relation == 1)) {
				map.put("trainee", trainee);
				return map;
			} else {
				obj = "对方信息保密";
				map.put("message", obj);
				return map;
			}

		} // 教练
		else if (obj instanceof Coach) {
			Coach coach = (Coach) obj;
			status = coach.getInfostatus();

			if (status == 2 || (status == 1 && relation == 1)) {
				map.put("coach", coach);
				return map;
			} else {
				obj = "对方信息保密";
				map.put("message", obj);
				return map;
			}
		} // 场馆
		else if (obj instanceof Venues) {
			List<String> img = venuesService.findImgById(otherUid);
			Venues venues = (Venues) obj;
			map.put("venues", venues);
			map.put("img", img);
			return map;
		}
		return null;
	}

	@RequestMapping("/otherUid")
	@ResponseBody
	public ModelAndView otherUid(int otheruid, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		// 登录的uid
		int uid = (int) session.getAttribute("uid");
		
		mv.addObject("uid", uid);
		mv.addObject("otheruid", otheruid);
		mv.setViewName("/html/checkOthersMessage.html");
		return mv;
	}
}
