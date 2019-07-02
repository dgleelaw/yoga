package com.woniuxy.yogasystem.service;

import java.util.List;

import com.woniuxy.yogasystem.pojo.Coach;
import com.woniuxy.yogasystem.pojo.Order_Form;
import com.woniuxy.yogasystem.pojo.Private_Course;
import com.woniuxy.yogasystem.pojo.Request_Message;
import com.woniuxy.yogasystem.pojo.Trainee;
import com.woniuxy.yogasystem.pojo.Venues;

public interface TraineeService {
	public Trainee findTrainee(int id);

	// 查看教练的基本信息
	public Coach findCoachBaiscMsg(int uid);

	// 查看教练的详细信息
	public Coach findCoachDetailMsg(int uid);

	// 查找场馆基本信息
	public Venues findVenuesBaiscMsg(int uid);

	// 查看场馆详细信息
	public Venues findVenuesDetailMsg(int uid);

	// 搜索教练功能
	// 根据条件教练的详细信息=条件检索：姓名，流派，认证，id
	public List<Coach> findCoachMsg(Coach coach);

	// 搜索场馆功能
	// 根据条件场馆的详细信息=条件检索：姓名，地址
	public List<Venues> findVenues(Venues venues);

	// 查询我的教练
	public List<Coach> findMyCoachMsg(int uid);

	// 查询我的场馆信息
	public List<Venues> findMyVenuesMsg(int uid);

	// 约私教
	public String sendCoachMsg(Trainee trainee, int uid2, int pid, int price, int vid);

	// 约场馆
	public String sendVenuesMsg(int uid1, int uid2, int price);

	// 查看通知消息
	public List<Request_Message> findMsgContent(int uid);

	// 根据学员id查询学员订单
	public List<Order_Form> findTraineeOrder(int uid);

	// 查看教练的课程信息表
	public List<Private_Course> findCoachCourse(int uid);
}
