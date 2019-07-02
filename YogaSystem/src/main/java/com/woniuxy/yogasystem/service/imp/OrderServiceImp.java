package com.woniuxy.yogasystem.service.imp;

import java.util.List;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.woniuxy.yogasystem.dao.CoachDao;
import com.woniuxy.yogasystem.dao.OrderDao;
import com.woniuxy.yogasystem.dao.TraineeDao;
import com.woniuxy.yogasystem.pojo.Coach;
import com.woniuxy.yogasystem.pojo.Order_Form;
import com.woniuxy.yogasystem.service.OrderService;
@Service
@Transactional
public class OrderServiceImp implements OrderService{
	@Resource
	private OrderDao orderDao;
	@Resource
	private CoachDao coachDao;

	@Override
	public List<Order_Form> findOrderMsg(int uid) {
		int tid = orderDao.findTraineeById(uid);//学员的id
		return orderDao.findOrderMsg(tid);
	}
	@Override
	public int showMoney(int uid) {
		return orderDao.showMoney(uid);
	}
	@Override
	public void updateMoney(int uid,int money) {
		orderDao.updateMoney(uid,money);
		
	}
	@Override
	public void updateStatus(String number) {
		orderDao.updateStatus(number);
		
	}
	@Override
	public void updateFlag(String number) {
		orderDao.updateFlag(number);
		
	}
	@Override
	public int findRole(int uid) {	
		return orderDao.findRole(uid);
	}
	@Override
	public List<Order_Form> findCoachOrderMsg(int uid) {
		int cid = coachDao.findCoachById(uid).getId();
		return orderDao.findCoachOrderMsg(cid);
	}
	@Override
	public String updateOrder(String number) {
		orderDao.updateOrder(number);
		return "教学完成";
	}
	@Override
	public void updateMoney(int vuid,int uid, int money, int cuid) {
		//减少学员余额
		orderDao.updateMoney(uid,money);
		//增加教练余额
		int demo = (int) ((int)money*(-0.6));
		orderDao.updateMoney(cuid, demo);
		//增加场馆余额
		orderDao.updateMoney(vuid, money*(-0.2));
		
	}

}
