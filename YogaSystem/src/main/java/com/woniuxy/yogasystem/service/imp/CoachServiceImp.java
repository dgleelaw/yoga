package com.woniuxy.yogasystem.service.imp;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.woniuxy.yogasystem.dao.CoachDao;
import com.woniuxy.yogasystem.dao.MessageDao;
import com.woniuxy.yogasystem.dao.OrderDao;
import com.woniuxy.yogasystem.dao.Private_CourseDao;
import com.woniuxy.yogasystem.dao.TraineeDao;
import com.woniuxy.yogasystem.dao.Trainee_CoachDao;
import com.woniuxy.yogasystem.dao.VenuesDao;
import com.woniuxy.yogasystem.pojo.Coach;
import com.woniuxy.yogasystem.pojo.Coach_Venues;
import com.woniuxy.yogasystem.pojo.Order_Form;
import com.woniuxy.yogasystem.pojo.Private_Course;
import com.woniuxy.yogasystem.pojo.Request_Message;
import com.woniuxy.yogasystem.pojo.Trainee;
import com.woniuxy.yogasystem.pojo.Venues;
import com.woniuxy.yogasystem.service.CoachService;

@Service("coachService")
@Transactional
public class CoachServiceImp implements CoachService{
	@Resource
	private CoachDao coachDao;
	@Resource
	private MessageDao messageDao;
	@Resource
	private OrderDao orderDao;
	@Resource
	private TraineeDao traineeDao;
	@Resource
	private Trainee_CoachDao trainee_CoachDao;
	@Resource
	private VenuesDao venuesDao;
	@Resource
	private Private_CourseDao private_CourseDao;
	
	
	@Override
	public Coach findCoach(int id) {
		return coachDao.findCoachById(id);
	}
	
	//教练向场馆发送请求
	@Override
	public String signVenues(int cid, int vid) {
		String result = "签约失败";
		Coach coach = coachDao.findCoachById(cid);
		Request_Message message = new Request_Message(cid, vid, "请求签约场馆", coach.getName(), coach.getImg(), 1,0);
		boolean flag = messageDao.insertMes(message);
		if(flag){
			result = "发送请求成功";
		}
		return result;
	}
	
	//教练查询请求
	@Override
	public List<Request_Message> findResMessage(int id) {
		List<Request_Message> message = messageDao.findResMessageById(id);
		return message;
	}

	//教练接受学员签约
	@Override
	public String adopt(Order_Form order,int mid,int uid1, int uid2,int pid) {
		Coach coach = coachDao.findCoachById(uid2);
		Trainee trainee = traineeDao.findTraineeByUId(uid1);
		order.setCid(coach.getId());
		order.setTid(trainee.getId());
		String number = UUID.randomUUID().toString()+""+System.currentTimeMillis();
		order.setNumber(number);
		//生成订单
		orderDao.addOrder(order);
		//删除申请
		messageDao.removeMesByPid(pid);
		//删除课程表
		private_CourseDao.remove(pid,trainee.getId());
		//添加学员教练关系
		trainee_CoachDao.add(trainee.getId(), coach.getId());
		//生成反馈信息给学员
		Request_Message message = new Request_Message(uid2, uid1, "教练同意了你的签约", coach.getName(), coach.getImg(), 1,1);
		messageDao.insertMes(message);
		return "签约成功";
	}

	@Override
	public String refuse(int cid, int tid, int mid) {
		Coach coach = coachDao.findCoachById(cid);
		//删除申请
		messageDao.removeMes(mid);
		//生成反馈信息
		Request_Message message = new Request_Message(cid, tid, "教练拒绝你的签约", coach.getName(), coach.getImg(), 1,1);
		messageDao.insertMes(message);
		return "拒绝成功";
	}

	
	
	@Override
	public List<Request_Message> findHintMessage(int id) {
		List<Request_Message> findMessageById = messageDao.findMessageById(id, 1);
		return findMessageById;
	}
	
	
	
	//场馆查看
	
	public List<Venues> findVenues(Venues venues){
		List<Venues> findVenues = venuesDao.findVenues(venues);
		return findVenues;
	}
	
	//查看我的学员
	@Override
	public List<Trainee> findTrainee(int uid) {
		Coach coach = coachDao.findCoachById(uid);
		int cid = coach.getId();
		List<Trainee> venuess = traineeDao.findTraineeByCid(cid);
		return venuess;
	}
	//查看我的场馆
	@Override
	public List<Venues> findMyVenues(int uid) {
		Coach coach = coachDao.findCoachById(uid);
		int cid = coach.getId();
		List<Venues> venuess = venuesDao.findVenuesByCid(cid);
		return venuess;
	}

	@Override
	public String removeMes(int id) {
		messageDao.removeMes(id);
		return "删除成功";
	}

	@Override
	public String adoptVenues(int uid, int vid, int mid) {
		Coach coach = coachDao.findCoachById(uid);
		Venues venues = venuesDao.findVenuesById(vid);
		//删除消息 
		messageDao.removeMes(mid);
		//生成关系
		new Coach_Venues(coach.getId(), vid, venues.getSalary());
		return "签约成功";
	}
	
	
	
}
