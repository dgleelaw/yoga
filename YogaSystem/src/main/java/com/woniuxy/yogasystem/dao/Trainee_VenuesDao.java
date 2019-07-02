package com.woniuxy.yogasystem.dao;

import org.apache.ibatis.annotations.Insert;

import com.woniuxy.yogasystem.pojo.Trainee_Venues;


public interface Trainee_VenuesDao {
	//插入签约学员信息表
	@Insert("insert into trainee_venues (tid,vid) values(#{tid},#{vid})")
	public void insertMsg(Trainee_Venues tv);
}
