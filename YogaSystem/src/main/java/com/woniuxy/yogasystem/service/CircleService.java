package com.woniuxy.yogasystem.service;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.woniuxy.yogasystem.pojo.Circle_of_Friend;

public interface CircleService {
	// 新增朋友圈
	public void insertCircle(Circle_of_Friend circle);

	// 查看关注人的新消息
	public List<Circle_of_Friend> findAllCircle(int uid);
	// 查看自己朋友圈

	public List<Circle_of_Friend> findMyCircle(int uid);
	// 删除朋友圈

	public void deleteAd(int id);
	//找到10条朋友圈
	public List<Circle_of_Friend> findTenCircle(int uid,int start);
	//找到自己的10条朋友圈
	public List<Circle_of_Friend> findMyTenCircle(int uid, int start);

}
