package com.woniuxy.yogasystem.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.woniuxy.yogasystem.pojo.Coach;
import com.woniuxy.yogasystem.pojo.Request_Message;
import com.woniuxy.yogasystem.pojo.Trainee;

public interface CoachDao {
	
	@Select("select * from coach where uid=#{id} and flag=0")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="phone",property="phone"),
		@Result(column="infostatus",property="infostatus"),
		@Result(column="addr",property="addr"),
		@Result(column="school",property="school"),
		@Result(column="authentication",property="authentication"),
		@Result(column="name",property="name"),
		@Result(column="img",property="img"),
		@Result(column="privatetime",property="privatetime"),
		@Result(column="uid",property="uid"),
		@Result(column="sex",property="sex"),
		@Result(column="salary",property="salary"),
		@Result(column="authstatus",property="authstatus"),
		@Result(column="uid",property="address",many=@Many(select="com.woniuxy.yogasystem.dao.AddressDao.findAddress"))
		//@Result(column="id",property="venues",many=@Many(select="com.woniuxy.yogasystem.dao.VenuesDao.findVenuesByCid")),
		//@Result(column="id",property="messages",many=@Many(select="com.woniuxy.yogasystem.dao.MessageDao.findMessageByCid"))
	})
	public Coach findCoachById(int id);
	
	
	
	@Select("select * from coach where id=#{cid}")
	public Coach findCoachByCid(int cid);

	
	@Select("select img from coach where uid=#{uid}")
	public String findHead(int uid);


	@Update("update coach set infostatus=2 where uid=#{uid}")
	public void updateinfostatus(int uid);
	
	

	
	
}
