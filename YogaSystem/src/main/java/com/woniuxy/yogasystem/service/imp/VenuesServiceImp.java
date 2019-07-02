package com.woniuxy.yogasystem.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.woniuxy.yogasystem.dao.CoachDao;
import com.woniuxy.yogasystem.dao.Coach_VenuesDao;
import com.woniuxy.yogasystem.dao.TraineeDao;
import com.woniuxy.yogasystem.dao.Trainee_VenuesDao;
import com.woniuxy.yogasystem.dao.VenuesDao;
import com.woniuxy.yogasystem.pojo.Coach;
import com.woniuxy.yogasystem.pojo.Coach_Venues;
import com.woniuxy.yogasystem.pojo.Request_Message;
import com.woniuxy.yogasystem.pojo.Trainee;
import com.woniuxy.yogasystem.pojo.Trainee_Venues;
import com.woniuxy.yogasystem.pojo.Venues;
import com.woniuxy.yogasystem.pojo.Venues_Img;
import com.woniuxy.yogasystem.service.VenuesService;
@Transactional
@Service("venuesService")
public class VenuesServiceImp implements VenuesService {
	@Resource
	private VenuesDao venuesDao;
	@Resource
	private Coach_VenuesDao coach_VenuesDao;
	@Resource
	private Trainee_VenuesDao trainee_VenuesDao;
	@Resource
	private TraineeDao traineeDao;
	@Resource
	private CoachDao coachDao;
	
	@Override
	public Venues findVenues(int id) {
		return venuesDao.findVenuesById(id);
	}

	// 搜索学员功能
	@Override
	public List<Trainee> findManyTraineeMsg(Trainee trainee) {
		return venuesDao.findManyTraineeMsg(trainee);
	}

	// 搜索教练功能
	@Override
	public List<Coach> findManyCoachMsg(Coach coach) {
		return venuesDao.findManyCoachMsg(coach);
	}

	// 查看我签约的学员
	@Override
	public List<Trainee> findSignTraineeMsg(int uid) {
		Venues venues = venuesDao.findVenuesByUId(uid);
		return venuesDao.findSignTraineeMsg(venues.getId());
	}

	// 查看我签约的场馆
	@Override
	public List<Coach> findSignCoachMsg(int uid) {
		Venues venues = venuesDao.findVenuesByUId(uid);
		return venuesDao.findSignCoachMsg(venues.getId());
	}

	// 签约教练发送的请求的消息
	@Override
	public String sendCoachMsg(Venues venues, int uid2, int price) {
		int uid1 = venues.getUid();// 会馆id
		String content = venues.getName() + "邀请了您";
		Request_Message rm = new Request_Message();
		rm.setContent(content);
		rm.setUid1(uid1);
		rm.setUid2(uid2);
		rm.setName(venues.getName());
		rm.setPrice(price);
		rm.setCharacter(2);
		rm.setType(0);
		// 将消息内容插入数据库中
		venuesDao.insertCoachMsg(rm);
		return "邀请成功";
	}

	// 展示通知消息
	@Override
	public List<Request_Message> findMsgContent(int uid) {
		return venuesDao.findMsgContent(uid);
	}

	// 接受教练申请消息
	@Override
	public String acceptCoachMsg(int vid, int cid, int id) {
		Venues venues = venuesDao.findVenuesByUId(vid);
		Coach coach = coachDao.findCoachById(cid);
		// 查看教练身份，分配工资
		String status = coach_VenuesDao.findCoachstatusByCid(cid);
		Coach_Venues cv = new Coach_Venues();
		if (status.equals("兼职")) {
			cv.setSalary(200);
		}
		if (status.equals("全职")) {
			cv.setSalary(1000);
		}
		if (status.equals("代课")) {
			cv.setSalary(500);
		}
		cv.setCid(coach.getId());
		cv.setVid(venues.getId());
		// 将信息插入教练签约工资表==签约成功
		coach_VenuesDao.insertMsg(cv);
		// 接受到的消息处理之后将flag=1
		venuesDao.handleAllMsg(id);
		//改变教练认证
		coachDao.updateinfostatus(coach.getUid());
		// 向教练发送消息
		Request_Message rm = answerMsg(vid, cid);
		venuesDao.insertCoachMsg(rm);
		return "签约成功";
	}

	// 接受学员申请消息
	@Override
	public String acceptTrainMsg(int uid1, int uid2, int mid) {
		Trainee trainee = traineeDao.findTraineeByUId(uid1);
		Venues venues = venuesDao.findVenuesByUId(uid2);
		// 插入学员和场馆关系信息表==签约成功
		Trainee_Venues tv = new Trainee_Venues();
		tv.setTid(trainee.getId());
		tv.setVid(venues.getId());
		trainee_VenuesDao.insertMsg(tv);
		// 接受到的消息处理之后将flag=1
		venuesDao.handleAllMsg(mid);
		// 向学员发送消息
		Request_Message rm = answerMsg(uid2, uid1);
		venuesDao.insertCoachMsg(rm);
		return "签约成功，消息已发送";
	}

	// 拒绝教练申请或者拒绝学员申请消息
	public String refuseMsg(int vid, int uid, int id) {
		// 接受到的消息处理之后将flag=1
		venuesDao.handleAllMsg(id);
		// 向学员或者教练发送消息
		Request_Message rm = refuseMsg(vid, uid);
		venuesDao.insertCoachMsg(rm);
		return "拒绝成功，消息已发送";
	}

	// 接受请求回馈语言
	public Request_Message answerMsg(int vid, int uid) {
		Request_Message rm = new Request_Message();
		rm.setUid1(vid);
		rm.setUid2(uid);
		Venues venues = venuesDao.findVenuesMsg(vid);
		String content = "场馆接受了您的请求";
		rm.setContent(content);
		rm.setType(1);
		rm.setCharacter(2);
		rm.setName(venues.getName());
		rm.setImg(venues.getImg());
		return rm;
	}

	// 拒绝请求回馈的消息
	public Request_Message refuseMsg(int vid, int uid) {
		Request_Message rm = new Request_Message();
		rm.setUid1(vid);
		rm.setUid2(uid);
		Venues venues = venuesDao.findVenuesMsg(vid);
		String content = venues.getName() + "拒绝了您的请求";
		rm.setContent(content);
		rm.setType(0);
		rm.setCharacter(2);
		return rm;
	}


	@Override
	public List<String> findImgById(int otherUid) {
		List<String> img=null;
		img=venuesDao.findImgById(otherUid);
		return img;
	}
}
