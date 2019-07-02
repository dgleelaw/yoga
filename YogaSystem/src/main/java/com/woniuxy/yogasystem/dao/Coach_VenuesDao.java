package com.woniuxy.yogasystem.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.woniuxy.yogasystem.pojo.Coach;
import com.woniuxy.yogasystem.pojo.Coach_Venues;
import com.woniuxy.yogasystem.pojo.Venues;

public interface Coach_VenuesDao {

	@Insert("insert into coach_venues(cid,vid,salary) values(#{cid},#{vid},#{salary})")
	public boolean add(Coach_Venues coach_Venues);

	// 查看教练的身份，是兼职，全职，代课。
	@Select("select authstatus from coach where uid=#{cid}")
	public String findCoachstatusByCid(int cid);

	// 插入签约教练信息表
	@Insert("Insert into coach_venues(cid,vid,salary) values(#{cid},#{vid},#{salary})")
	public void insertMsg(Coach_Venues coach_venues);

	// 查询教练签约的场馆vid及场馆的详细信息根据教练cid
	@Select("select * from coach_venues inner join venues on coach_venues.vid=venues.uid"
			+ " where cid=#{uid} and venues.flag=0")
	public List<Venues> findVenuesDetailMsg(int uid);

	// 查看场馆下所有教练的信息
	@Select("select * from coach_venues inner join venues on coach_venues.vid=venues.uid"
			+ " where vid=#{uid} and flag=0")
	public List<Coach> findCoachDetailMsg(int uid);
}
