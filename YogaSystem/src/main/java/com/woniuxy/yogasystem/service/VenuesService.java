package com.woniuxy.yogasystem.service;

import java.util.List;

import org.springframework.ui.ModelMap;

import com.woniuxy.yogasystem.pojo.Coach;
import com.woniuxy.yogasystem.pojo.Request_Message;
import com.woniuxy.yogasystem.pojo.Trainee;
import com.woniuxy.yogasystem.pojo.Venues;

public interface VenuesService {
	public Venues findVenues(int id);

	// 会馆向教练发送消息
	public String sendCoachMsg(Venues venues, int uid2, int price);

	// 展示消息
	public List<Request_Message> findMsgContent(int uid);

	// 根据条件查询多个或一个教练信息
	public List<Coach> findManyCoachMsg(Coach coach);

	// 根据场馆uid查询场馆下所有教练信息
	public List<Coach> findSignCoachMsg(int uid);

	// 根据条件（地址，姓名）去查询学员信息
	public List<Trainee> findManyTraineeMsg(Trainee trainee);

	// 查看我签约的会馆
	public List<Trainee> findSignTraineeMsg(int uid);

	// 签约教练
	public String acceptCoachMsg(int vid, int cid, int id);

	// 场馆接受学员的申请消息
	public String acceptTrainMsg(int uid1, int uid2, int mid);
	
	List<String> findImgById(int otherUid);
}
