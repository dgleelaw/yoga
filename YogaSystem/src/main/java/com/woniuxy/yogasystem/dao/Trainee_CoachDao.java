package com.woniuxy.yogasystem.dao;

import org.apache.ibatis.annotations.Insert;

public interface Trainee_CoachDao {
	
	@Insert("insert into trainee_coach(tid,cid) values(#{param1},#{param2})")
	public boolean add(int tid,int cid);
}
