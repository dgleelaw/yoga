package com.woniuxy.yogasystem.service;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.woniuxy.yogasystem.pojo.Follows;

public interface FollowsService {
	// 查询互相关注的人的uid
	public List<Follows> FindAllFollowsUidByOwnerUid(Integer uid);

	// 修改显示信息的权限,当页面修改权限时
	public void UpdateStatus(int status, int followuid);
	
	//插入关注用户
	public void insertFollows(Follows follows);
	//取消关注
	public void deleteFollows(Follows follows);
	//查找所有关注的人
	public List<Follows> findAllFollows(int uid);
	//关注我的人
	public List<Follows> findMyFollows(int uid);
	
	
	

	int findFollowByUid(int uid, int otherUid);
}
