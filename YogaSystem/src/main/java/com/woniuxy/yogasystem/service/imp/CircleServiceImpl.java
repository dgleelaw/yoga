package com.woniuxy.yogasystem.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.woniuxy.yogasystem.dao.CircleDao;
import com.woniuxy.yogasystem.pojo.Circle_of_Friend;
import com.woniuxy.yogasystem.service.CircleService;
@Transactional
@Service("CircleService")
public class CircleServiceImpl implements CircleService{
 	@Autowired
	private CircleDao circleDao;
	@Override
	public void insertCircle(Circle_of_Friend circle) {
		// TODO Auto-generated method stub
		circleDao.insertCircle(circle);
	}
	@Override
	public List<Circle_of_Friend> findAllCircle(int uid) {
		// TODO Auto-generated method stub
		return circleDao.findAllCircle(uid);
	}
	@Override
	public List<Circle_of_Friend> findMyCircle(int uid) {
		// TODO Auto-generated method stub
		return circleDao.findMyCircle(uid);
	}
	@Override
	public void deleteAd(int id) {
		// TODO Auto-generated method stub
		circleDao.deleteAd(id);
	}
	@Override
	public List<Circle_of_Friend> findTenCircle(int uid, int start) {
		// TODO Auto-generated method stub
		return circleDao.findTenCircle(uid, start);
	}
	@Override
	public List<Circle_of_Friend> findMyTenCircle(int uid, int start) {
		// TODO Auto-generated method stub
		return circleDao.findMyTenCircle(uid, start);
	}

}
