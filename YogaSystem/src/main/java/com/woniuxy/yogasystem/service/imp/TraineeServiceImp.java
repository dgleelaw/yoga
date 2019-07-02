package com.woniuxy.yogasystem.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.woniuxy.yogasystem.dao.CoachDao;
import com.woniuxy.yogasystem.dao.TraineeDao;
import com.woniuxy.yogasystem.pojo.Coach;
import com.woniuxy.yogasystem.pojo.Order_Form;
import com.woniuxy.yogasystem.pojo.Private_Course;
import com.woniuxy.yogasystem.pojo.Request_Message;
import com.woniuxy.yogasystem.pojo.Trainee;
import com.woniuxy.yogasystem.pojo.Venues;
import com.woniuxy.yogasystem.service.TraineeService;
@Transactional
@Service("traineeService")
public class TraineeServiceImp implements TraineeService {
	@Resource
	private CoachDao coachDao;
	@Resource
	private TraineeDao traineeDao;
	
	
	public Trainee findTrainee(int id) {
		return traineeDao.findTraineeById(id);
	}

	

	// 查看教练的基本信息
	@Override
	public Coach findCoachBaiscMsg(int uid) {
		return traineeDao.findCoachBaiscMsg(uid);
	}

	// 查看教练的详细信息
	@Override
	public Coach findCoachDetailMsg(int uid) {
		return traineeDao.findCoachDetailMsg(uid);
	}

	// 查看场馆的基本信息
	@Override
	public Venues findVenuesBaiscMsg(int uid) {
		return traineeDao.findVenuesBaiscMsg(uid);
	}

	// 查看场馆的详细信息
	@Override
	public Venues findVenuesDetailMsg(int uid) {
		return traineeDao.findVenuesDetailMsg(uid);
	}

	// 搜索教练功能
	// 根据条件教练的详细信息=条件检索：姓名，流派，认证，id
	@Override
	public List<Coach> findCoachMsg(Coach coach) {
		return traineeDao.findCoachMsg(coach);
	}

	// 搜索场馆功能
	// 根据条件场馆的详细信息=条件检索：姓名，地址
	@Override
	public List<Venues> findVenues(Venues venues) {
		return traineeDao.findVenues(venues);
	}

	// 查询我的教练
	@Override
	public List<Coach> findMyCoachMsg(int uid) {
		Trainee trainee = traineeDao.findTraineeByUId(uid);
		List<Coach> findMyCoachMsg = traineeDao.findMyCoachMsg(trainee.getId());
		return findMyCoachMsg;
	}

	// 查看我的场馆信息
	@Override
	public List<Venues> findMyVenuesMsg(int uid) {
		Trainee trainee = traineeDao.findTraineeByUId(uid);
		return traineeDao.findMyVenuesMsg(trainee.getId());
	}
	// 约私教
	// url:/trainee//appointCoach
	// 传递的参数：学员uid1，type=0（0申请，1代表消息）
	// ,学员余额balanc,学员的姓名name，学员的头像，角色character
	// 被邀请者（教练）uid2，教练私教课程pid，教练price，vid教练签约的哪个场馆

	// 学员id，学员头像，学员姓名，教练id，教练课程id，教练价格，场馆id

	@Override
	public String sendCoachMsg(Trainee trainee, int uid2, int pid, int price, int vid) {
		int traineeId = trainee.getUid();// 学员id
		int traineeBalance = traineeDao.selectBalance(traineeId);
		// 根据教练的课程pid查询教练对应的课程价格
		if (traineeBalance < price) {
			return "您的账户余额已不足，请充值";
		}
		Coach coach = coachDao.findCoachByCid(uid2);
		Trainee currentTrainee = traineeDao.findTraineeByUId(traineeId);
		// 插入消息表
		String content = currentTrainee.getName() + "学员预约了您";
		Request_Message rm = new Request_Message();
		rm.setContent(content);
		rm.setImg(currentTrainee.getImg());
		rm.setName(currentTrainee.getName());
		rm.setUid1(traineeId);
		rm.setUid2(coach.getUid());
		rm.setCharacter(0);
		rm.setType(0);
		rm.setPrice(price);
		rm.setPid(pid);
		rm.setVid(vid);
		// 将申请私教的消息插入数据库中
		traineeDao.insertCoachMsg(rm);
		return "邀请成功";
	}

	// 学员约场馆
	@Override
	public String sendVenuesMsg(int uid1, int uid2, int price) {
		Trainee trainee = traineeDao.findTraineeByUId(uid1);
		int traineeBalance = traineeDao.selectBalance(uid1);
		// 根据场馆的课程pid查询教练对应的课程价格
		if (traineeBalance < price) {
			return "您的账户余额已不足，请充值";
		}
		// 插入消息表
		String content = trainee.getName() + "请求成为学员";
		Request_Message rm = new Request_Message();
		rm.setContent(content);
		rm.setImg(trainee.getImg());
		rm.setName(trainee.getName());
		rm.setUid1(uid1);
		rm.setUid2(uid2);
		rm.setCharacter(0);
		rm.setType(0);
		rm.setPrice(price);
		// 将申请私教的消息插入数据库中
		traineeDao.insertVenuesMsg(rm);
		return "申请信息已发送，请等待回复";
	}

	// 查看通知消息
	@Override
	public List<Request_Message> findMsgContent(int uid) {
		return traineeDao.findMsgContent(uid);
	}

	// 查看我的订单
	@Override
	public List<Order_Form> findTraineeOrder(int uid) {
		return traineeDao.findTraineeOrder(uid);
	}

	@Override
	public List<Private_Course> findCoachCourse(int uid) {
		Coach coach = coachDao.findCoachById(uid);
		return traineeDao.findCoachCourse(coach.getId());
	}

}
