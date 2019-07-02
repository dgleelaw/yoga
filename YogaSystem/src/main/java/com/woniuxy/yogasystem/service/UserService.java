package com.woniuxy.yogasystem.service;

import java.util.List;

import com.woniuxy.yogasystem.pojo.Address;
import com.woniuxy.yogasystem.pojo.ApplyMessage;
import com.woniuxy.yogasystem.pojo.Coach;
import com.woniuxy.yogasystem.pojo.Trainee;
import com.woniuxy.yogasystem.pojo.User;
import com.woniuxy.yogasystem.pojo.Venues;

public interface UserService {
	// 检查账号是否存在
	public String checkacc(String acc);

	// 注册账号
	public boolean register(User user);

	// 注册管理员
	public boolean regManager(User user);

	// 登录
	public User login(User user);
	//短信登陆
	public User mesLogin(User user);

	// 找回密码
	public int reset(User user);

	/*
	 * 管理员操作
	 */
	// 查询applymessage里所有教练申请请求
	public List<ApplyMessage> findCoachApply();

	// 查询applymessage里所有场馆申请请求
	public List<ApplyMessage> findVenuesApply();

	// 同意并添加教练或场馆
	public void addAgree(int uid, int role);

	// 拒绝教练或者场馆
	public void refuse(int uid, int role);

	/*
	 * 学员操作
	 */
	// 学员补全资料
	public String regTrainee(Trainee trainee, Address address, int role);

	// 查询学员全部信息
	public Trainee findTrainee(int uid);

	// 修改资料更新学员信息
	public String updateTraineeMes(Trainee trainee, Address address);

	// 查询学员地址
	public Address findTraineeAdd(int uid);

	/*
	 * 场馆操作
	 */

	// 场馆补全资料
	public String regVenues(Venues venues, Address address, int role, List<String> piclist);

	// 查询场馆全部信息
	public Venues findVenues(int uid);

	// 查询场馆地址
	public Address findVenuesAdd(int uid);

	// 修改资料更新场馆信息
	public String updateVenuesMes(Venues venues, Address address, List<String> piclist);

	// 查询场馆是否被注册
	public String checkName(String name);

	// 申请成为场馆
	public String applyVenues(Venues venues, Address address, int role, List<String> piclist);

	/*
	 * 教练操作
	 */
	// 教练补全资料
	public String regCoach(Coach coach, Address address, int role);

	// 查询教练全部信息
	public Coach findCoach(int uid);

	// 查询教练地址
	public Address findCochAdd(int uid);

	// 修改资料更新教练信息
	public String updateCoachMes(Coach coach, Address address);

	// 申请成为教练
	public String applyCoach(Coach coach, Address address, int role);

	// 查询头像
	public String findhead(int role, int uid);
	
	
	
	
    public List<Coach> selectAllCoach();

    
    public Coach selectCoach(Integer uid);

    
    public Trainee selectTrainee(Integer uid);
    
    
    public Venues selectVenue(Integer uid);

    
    public List<Venues> selectAllVenues();
    
    
    public Object findAllInfoByUid(int uid);

}
