package com.woniuxy.yogasystem.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.aliyuncs.exceptions.ClientException;
import com.woniuxy.yogasystem.pojo.Address;
import com.woniuxy.yogasystem.pojo.ApplyMessage;
import com.woniuxy.yogasystem.pojo.Coach;
import com.woniuxy.yogasystem.pojo.Trainee;
import com.woniuxy.yogasystem.pojo.User;
import com.woniuxy.yogasystem.pojo.Venues;
import com.woniuxy.yogasystem.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Resource
	private UserService userService;

	// 注册
	@RequestMapping("/register")
	@ResponseBody
	public String register(HttpServletRequest request, String code, User user) {
		String result = "失败";
		System.out.println(user);
		// System.out.println(i);
		System.out.println(code);
		HttpSession session = request.getSession();
		Object reCode = session.getAttribute("code");
		String realCode = (String) reCode;
		 if(code.equals(realCode)){
			 session.removeAttribute("code");
			if( userService.register(user)){
				 result="成功";
			}
		 }else{
			 result="验证码错误！";
			 session.removeAttribute("code");
		 }
		return result;
	}


	//重置密码
	@RequestMapping("/reset")
	@ResponseBody
	public String reset(HttpServletRequest request,String code,User user){
		String result="失败";
		
		 HttpSession session= request.getSession();
		 Object reCode=session.getAttribute("code");
		 String realCode=(String) reCode;
		 if(code.equals(realCode)){
			 session.removeAttribute("code");
			if( userService.reset(user)>0){
				 result="成功";
			}
		 }else{
			 result="验证码错误！";
			 session.removeAttribute("code");
		 }
		return result;
		
	}

	// 管理员注册
	@RequestMapping("/regmanager")
	@ResponseBody
	public String regManager(User user) {
		String result = "失败";
		if (userService.regManager(user)) {
			result = "成功";
		}
		return result;

	}

	// 验证码
	@RequestMapping("/getcode")
	@ResponseBody
	public String getCode(HttpServletRequest request, User user, int i) throws ClientException {
		String result = "短信发送失败";
		int g = (int) ((Math.random() * 9 + 1) * 1000);
		String code = Integer.toString(g);
		HttpSession session = request.getSession();
		session.setAttribute("code", code);
		System.out.println("发送的验证码为：" + code);
		System.out.println("接收到的验证码类型为" + i);
		/*
		 * SendSmsResponse response = CodeUtil.sendSms(user.getAcc(), code, i);
		 * if(response.getMessage().equals("OK")){ result= "短信发送成功！" ; return
		 * result; }
		 */

		return "短信发送成功";
	}

	// 检查注册手机号是否重复
	@RequestMapping("/checkacc")
	@ResponseBody
	public String checkAcc(String acc) {
		String result = "请输入合法的账户";
		if (acc.isEmpty()) {
			return result;
		} else {
			result = userService.checkacc(acc);
		}

		return result;

	}

	// 登录
	@RequestMapping("/login")
	@ResponseBody
	public User login(HttpServletRequest request, User user) {
		System.out.println("用户账号" + user.getAcc());
		Subject currentUser = SecurityUtils.getSubject();
		if (!currentUser.isAuthenticated()) {
			UsernamePasswordToken token = new UsernamePasswordToken(user.getAcc(), user.getPwd());
			try {
				currentUser.login(token);
				System.out.println("认证成功");
				User user2 = null;
				user2 = userService.login(user);
				HttpSession session = request.getSession();
				// 0学员 1教练 2场馆 3管理 4超管 5游客
				if (user2.getRole() == 2) {// 如果登录时会管管理员
					Venues venues = userService.findVenues(user2.getId());
					if (venues != null) {
						System.out.println("场馆信息" + venues);
						session.setAttribute("venues", venues);
						session.setAttribute("name", venues.getName());
						session.setAttribute("headimg", venues.getImg());
					}
				} else if (user2.getRole() == 1) {// 登录时教练
					Coach coach = userService.findCoach(user2.getId());
					if (coach != null) {
						System.out.println("教练信息" + coach);
						session.setAttribute("coach", coach);
						session.setAttribute("name", coach.getName());
						session.setAttribute("headimg", coach.getImg());

					}
				} else if (user2.getRole() == 0) {// 学员登录
					Trainee trainee = userService.findTrainee(user2.getId());
					if (trainee != null) {
						System.out.println("学员信息" + trainee);
						session.setAttribute("trainee", trainee);
						session.setAttribute("name", trainee.getName());
						session.setAttribute("headimg", trainee.getImg());
					}
				}
				session.setAttribute("role", user2.getRole());
				System.out.println(session.getAttribute("role"));
				session.setAttribute("uid", user2.getId());
				session.setAttribute("acc", user2.getAcc());
				System.out.println(user2);
				return user2;
			} catch (Exception e) {
				System.out.println("账号不存在");
			} 
		}
		return null;
	}
	
	
	//短信登录
	@RequestMapping("/meslogin")
	@ResponseBody
	public ModelMap mesLogin(HttpServletRequest request,User user,String code){
		System.out.println("用户账号"+user.getAcc());
		User user2=null;
		ModelMap map =new ModelMap();
		String result="验证码错误！";
		HttpSession session= request.getSession();
		 Object reCode=session.getAttribute("code");
		 String realCode=(String) reCode;
		 if(code.equals(realCode)){
			 session.removeAttribute("code");
			 user2=userService.mesLogin(user);
			 if(user2==null){
				 result="用户不存在！";
				 map.put("result", result);
				 map.put("user", user2);
					return map;
				}else{
					
				//0学员 1教练 2场馆 3管理 4超管  5游客
				if(user2.getRole()==2){//如果登录时会管管理员
					Venues venues=userService.findVenues(user2.getId());
					if(venues!=null){
						System.out.println("场馆信息"+venues);
						session.setAttribute("venues", venues);
						session.setAttribute("name", venues.getName());
						session.setAttribute("headimg", venues.getImg());
					}
				}else if(user2.getRole()==1){//登录时教练
					Coach coach = userService.findCoach(user2.getId());
					if(coach!=null){
						System.out.println("教练信息"+coach);
						session.setAttribute("coach", coach);
						session.setAttribute("name", coach.getName());
						session.setAttribute("headimg", coach.getImg());

					}
				}else if(user2.getRole()==0){//学员登录
					Trainee trainee = userService.findTrainee(user2.getId());
					if(trainee!=null){
						System.out.println("学员信息"+trainee);
						session.setAttribute("trainee", trainee);
						session.setAttribute("name", trainee.getName());
						session.setAttribute("headimg", trainee.getImg());
					}
				}
				Subject currentUser = SecurityUtils.getSubject();
				UsernamePasswordToken token = new UsernamePasswordToken(user2.getAcc(),user2.getPwd());
				currentUser.login(token);
				session.setAttribute("role", user2.getRole());
			  session.setAttribute("uid", user2.getId());
			  session.setAttribute("acc", user2.getAcc());
				System.out.println(user2);
				result="登陆成功";
				 map.put("result", result);
				 map.put("user", user2);
				return map;
				
				}
		 }else{
			 session.removeAttribute("code");
			 map.put("result", result);
			 map.put("user", user2);
			 return map;
		 }
		
		
		
	}
	
	

	// 上传头像图片
	@RequestMapping("/upload")
	@ResponseBody
	public String upload(@RequestParam(name = "picture") MultipartFile picture, HttpServletRequest request)
			throws IllegalStateException, IOException {
		// 获取文件名
		String filename = picture.getOriginalFilename();
		// 获取保存文件的路径
		String path = request.getServletContext().getRealPath("headimg");
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
		String realpath = "/headimg/" + filename;
		HttpSession session = request.getSession();
		session.setAttribute("headpic", realpath);
		System.out.println(filename);
		return "上传成功";
	}

	// 上传场馆图片
	@RequestMapping("/uploadpic")
	@ResponseBody
	public String uploadpic(@RequestParam(name = "file") MultipartFile[] picture, HttpServletRequest request)
			throws IllegalStateException, IOException {

		String result = null;
		// Map<String, String>map=new HashMap<String, String>();
		List<String> piclist = new ArrayList<>();
		// 获取文件名
		System.out.println(1);
		System.out.println(picture.length);
		MultipartFile pic = null;
		for (int i = 0; i < picture.length; i++) {
			System.out.println(3);
			pic = picture[i];
			String filename = pic.getOriginalFilename();
			// 获取保存文件的路径
			String path = request.getServletContext().getRealPath("veneusimg");
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
			pic.transferTo(file);
			// map.put("pic", );
			piclist.add("/veneusimg/" + filename);

		}
		HttpSession session = request.getSession();
		session.setAttribute("piclist", piclist);
		for (int i = 0; i < piclist.size(); i++) {
			System.out.println(piclist.get(i));
		}
		return result;
	}

	/*
	 * 学员操作
	 */
	// 学员添加信息
	@RequestMapping("/regtrainee")
	@ResponseBody
	public String regTrainee(HttpServletRequest request, Trainee trainee, Address address, int role) {
		System.out.println(role);
		String result = "失败";
		HttpSession session = request.getSession();
		Object reuid = session.getAttribute("uid");
		Object reacc = session.getAttribute("acc");
		Object reimg = session.getAttribute("headpic");
		String img = (String) reimg;
		int uid = Integer.parseInt(reuid.toString());
		String phone = (String) reacc;
		trainee.setPhone(phone);
		trainee.setUid(uid);
		trainee.setImg(img);
		address.setUid(uid);
		System.out.println(trainee);
		System.out.println(address);
		if (trainee.getImg() == null) {
			String defaultImg = "/headimg/yuga.jpg";
			trainee.setImg(defaultImg);
		}
		if (userService.regTrainee(trainee, address, role).contains("成功")) {
			result = "信息已提交";
		}
		return result;
	}

	// 请求学员信息
	@RequestMapping("/modifytrainee")
	@ResponseBody
	public ModelMap traineeMes(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Object reuid = session.getAttribute("uid");
		Object rerole = session.getAttribute("role");
		int uid = Integer.parseInt(reuid.toString());
		int role = Integer.parseInt(rerole.toString());
		ModelMap map = new ModelMap();
		// Trainee trainee=(Trainee) session.getAttribute("trainee");
		Trainee trainee = userService.findTrainee(uid);
		map.put("message", trainee);
		return map;
		// String
		// realpath="/headimg/3c3f328e-f4b8-4a7a-b516-7c3e224943b9u=2724886373,3500404552&fm=26&gp=0.jpg";
	}

	// 修改学员资料
	@RequestMapping("/modifytraineemes")
	@ResponseBody
	public String UpdateTraineeMes(HttpServletRequest request, Trainee trainee, Address address) {
		String result = "失败";
		HttpSession session = request.getSession();
		Object reuid = session.getAttribute("uid");
		Object reacc = session.getAttribute("acc");
		Object reimg = session.getAttribute("headpic");
		String img = (String) reimg;
		int uid = Integer.parseInt(reuid.toString());
		String phone = (String) reacc;
		trainee.setUid(uid);
		trainee.setImg(img);
		trainee.setPhone(phone);
		if (trainee.getImg() == null) {
			String oldImg = (String) session.getAttribute("headimg");
			trainee.setImg(oldImg);
		}
		result = userService.updateTraineeMes(trainee, address);
		return result;

	}

	/*
	 * 教练操作
	 */
	// 添加教练信息
	/*
	 * @RequestMapping("/regcoach")
	 * 
	 * @ResponseBody public String regCoach(HttpServletRequest request,Coach
	 * coach,Address address,int role){ System.out.println(role); String
	 * result="失败"; HttpSession session= request.getSession(); Object
	 * reuid=session.getAttribute("uid"); Object
	 * reacc=session.getAttribute("acc"); Object reimg =
	 * session.getAttribute("headpic"); String img = (String) reimg; int uid=
	 * Integer.parseInt(reuid.toString()); String phone=(String) reacc;
	 * coach.setPhone(phone); coach.setUid(uid); coach.setImg(img);
	 * address.setUid(uid); System.out.println(coach);
	 * System.out.println(address); if(userService.regCoach(coach, address,
	 * role).contains("成功")){ result="信息已提交"; } return result; }
	 */

	// 申请教练
	@RequestMapping("/applycoach")
	@ResponseBody
	public String applyCoach(HttpServletRequest request, Coach coach, Address address, int role) {
		String result = "申请失败";
		HttpSession session = request.getSession();
		Object reuid = session.getAttribute("uid");
		Object reacc = session.getAttribute("acc");
		Object reimg = session.getAttribute("headpic");
		String img = (String) reimg;
		int uid = Integer.parseInt(reuid.toString());
		String phone = (String) reacc;
		coach.setPhone(phone);
		coach.setUid(uid);
		coach.setImg(img);
		address.setUid(uid);
		if (coach.getImg() == null) {
			String defaultImg = "/headimg/yuga.jpg";
			coach.setImg(defaultImg);
		}
		result = userService.applyCoach(coach, address, role);
		return result;
	}

	// 请求教练信息
	@RequestMapping("/modifycoach")
	@ResponseBody
	public ModelMap coachMes(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Object reuid = session.getAttribute("uid");
		Object rerole = session.getAttribute("role");
		int uid = Integer.parseInt(reuid.toString());
		int role = Integer.parseInt(rerole.toString());
		ModelMap map = new ModelMap();
		// Coach coach=(Coach) session.getAttribute("coach");
		Coach coach = userService.findCoach(uid);
		map.put("message", coach);
		return map;
		// String
		// realpath="/headimg/3c3f328e-f4b8-4a7a-b516-7c3e224943b9u=2724886373,3500404552&fm=26&gp=0.jpg";
	}

	// 修改教练资料
	@RequestMapping("/modifycoachmes")
	@ResponseBody
	public String UpadteCoachMes(HttpServletRequest request, Coach coach, Address address) {
		String result = "失败";
		HttpSession session = request.getSession();
		Object reuid = session.getAttribute("uid");
		Object reacc = session.getAttribute("acc");
		Object reimg = session.getAttribute("headpic");
		String img = (String) reimg;
		int uid = Integer.parseInt(reuid.toString());
		String phone = (String) reacc;
		coach.setPhone(phone);
		coach.setUid(uid);
		coach.setImg(img);
		address.setUid(uid);
		if (coach.getImg() == null) {
			String oldImg = (String) session.getAttribute("headimg");
			coach.setImg(oldImg);
		}
		System.out.println(coach);
		System.out.println(address);
		result = userService.updateCoachMes(coach, address);
		return result;

	}

	/*
	 * 场馆操作
	 */
	// 检查场馆名字是否可用
	@RequestMapping("/checkname")
	@ResponseBody
	public String checkName(String name) {
		String result = "场馆已被注册！";
		result = userService.checkName(name);
		return result;

	}

	/*
	 * //添加场馆信息
	 * 
	 * @RequestMapping("/regvenues")
	 * 
	 * @ResponseBody public String regCoach(HttpServletRequest request,Venues
	 * venues,Address address,int role){ System.out.println(role); String
	 * result="失败"; HttpSession session= request.getSession(); Object
	 * reuid=session.getAttribute("uid"); Object
	 * reacc=session.getAttribute("acc"); Object reimg =
	 * session.getAttribute("headpic"); Object
	 * repiclist=session.getAttribute("piclist"); List<String>
	 * piclist=(List<String>) repiclist;
	 * System.out.println("图片是否取出"+piclist.size()); for (int i = 0; i <
	 * piclist.size(); i++) { System.out.println(piclist.get(i)); } String img =
	 * (String) reimg; int uid= Integer.parseInt(reuid.toString()); String
	 * phone=(String) reacc; venues.setPhone(phone); venues.setUid(uid);
	 * venues.setImg(img); address.setUid(uid); System.out.println(venues);
	 * System.out.println(address); if(userService.regVenues(venues, address,
	 * role,piclist).contains("成功")){ result="信息已提交"; }
	 * 
	 * return result;
	 * 
	 * }
	 */
	// 申请成为场馆
	@RequestMapping("/applyvenues")
	@ResponseBody
	public String regCoach(HttpServletRequest request, Venues venues, Address address, int role) {
		System.out.println(role);
		String result = "失败";
		HttpSession session = request.getSession();
		Object reuid = session.getAttribute("uid");
		Object reacc = session.getAttribute("acc");
		Object reimg = session.getAttribute("headpic");
		Object repiclist = session.getAttribute("piclist");
		List<String> piclist = (List<String>) repiclist;
		System.out.println("图片是否取出" + piclist.size());
		for (int i = 0; i < piclist.size(); i++) {
			System.out.println(piclist.get(i));
		}
		String img = (String) reimg;
		int uid = Integer.parseInt(reuid.toString());
		String phone = (String) reacc;
		venues.setPhone(phone);
		venues.setUid(uid);
		venues.setImg(img);
		address.setUid(uid);
		System.out.println(venues);
		System.out.println(address);
		if (venues.getImg() == null) {
			String defaultImg = "/headimg/yuga.jpg";
			venues.setImg(defaultImg);
		}
		result = userService.applyVenues(venues, address, role, piclist);
		return result;
	}

	// 申请场馆信息
	@RequestMapping("/modifyvenues")
	@ResponseBody
	public ModelMap VenuesMes(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Object reuid = session.getAttribute("uid");
		Object rerole = session.getAttribute("role");
		int uid = Integer.parseInt(reuid.toString());
		int role = Integer.parseInt(rerole.toString());
		ModelMap map = new ModelMap();
		// Venues venues= (Venues) session.getAttribute("venues");
		Venues venues = userService.findVenues(uid);
		map.put("message", venues);
		return map;
		// String
		// realpath="/headimg/3c3f328e-f4b8-4a7a-b516-7c3e224943b9u=2724886373,3500404552&fm=26&gp=0.jpg";
	}

	// 修改场馆资料
	@RequestMapping("/modifyvenuesmes")
	@ResponseBody
	public String UpadteVenuesMes(HttpServletRequest request, Venues venues, Address address, int role) {
		System.out.println(role);
		String result = "失败";
		HttpSession session = request.getSession();
		Object reuid = session.getAttribute("uid");
		Object reacc = session.getAttribute("acc");
		Object reimg = session.getAttribute("headpic");
		Object repiclist = session.getAttribute("piclist");
		List<String> piclist = (List<String>) repiclist;
		System.out.println("图片是否取出" + piclist.size());
		for (int i = 0; i < piclist.size(); i++) {
			System.out.println(piclist.get(i));
		}
		String img = (String) reimg;
		int uid = Integer.parseInt(reuid.toString());
		String phone = (String) reacc;
		venues.setPhone(phone);
		venues.setUid(uid);
		venues.setImg(img);
		address.setUid(uid);
		if (venues.getImg() == null) {
			String oldImg = (String) session.getAttribute("headimg");
			venues.setImg(oldImg);
		}
		System.out.println(venues);
		System.out.println(address);
		result = userService.updateVenuesMes(venues, address, piclist);
		return result;

	}

	/*
	 * 管理员操作
	 */
	// 查询applymessage里所有教练申请请求
	@RequestMapping("/findCoachMessage")
	@ResponseBody
	public List<ApplyMessage> searchCoachMes() {
		List<ApplyMessage> CoachApply = new ArrayList<>();
		CoachApply = userService.findCoachApply();
		return CoachApply;
	}

	// 查询applymessage里所有场馆申请请求
	@RequestMapping("/findVenuesMessage")
	@ResponseBody
	public List<ApplyMessage> searchVenuesMes() {
		List<ApplyMessage> CoachApply = new ArrayList<>();
		CoachApply = userService.findVenuesApply();
		return CoachApply;
	}

	// 同意申请
	@RequestMapping("/agree")
	@ResponseBody
	public void agreeApply(int uid1, int role) {
		userService.addAgree(uid1, role);
	}

	// 拒绝申请
	@RequestMapping("/refuse")
	@ResponseBody
	public void refuse(int uid1, int role) {
		userService.refuse(uid1, role);
	}

	@ResponseBody
	@RequestMapping("/findhead")
	public String findhead(HttpSession session) {
		int role = (int) session.getAttribute("role");
		int uid = (int) session.getAttribute("uid");
		System.out.println(role + ":" + uid);
		return userService.findhead(role, uid);
	}
	
	
	public ModelAndView mv = new ModelAndView();

	@RequestMapping("/chat")
	@ResponseBody
	public ModelAndView test(String mineId, String otherId,HttpSession session) {
		System.out.println(mineId);
		String name =(String)session.getAttribute("name");
		mv.addObject("myName",name);
		mv.addObject("mineAcc", mineId);
		mv.addObject("otherAcc", otherId);
		
		mv.setViewName("/html/chatOne.html");
		return mv;
	}

	@RequestMapping("/chat1")
	@ResponseBody
	public ModelAndView test(String otherId,HttpSession session) {
		int mineId = (int)session.getAttribute("uid");
		System.out.println(mineId);
		System.out.println(otherId);
		String name =(String)session.getAttribute("name");
		mv.addObject("myName",name);
		mv.addObject("mineAcc", mineId);
		mv.addObject("otherAcc", otherId);
		
		mv.setViewName("/html/chatOne.html");
		return mv;
	}
	
	
	
	@RequestMapping("/chatroom")
	@ResponseBody
	public ModelAndView testRoom(String roomId,HttpSession session) {
		String name =(String)session.getAttribute("name");
		int id = (int)session.getAttribute("uid");
		mv.addObject("myName",name);
		mv.addObject("mineAcc", String.valueOf(id));
		mv.addObject("roomAcc", roomId);
		mv.setViewName("/html/chatRoom.html");
		return mv;
	}

	@RequestMapping("/otherInfo")
	public String otherInfo(ModelMap map, int uid, String img, String name) {
		map.put("uid", uid);
		map.put("img", img);
		map.put("name", name);
		System.out.println(map);
		return "/html/text.html";

	}

}
