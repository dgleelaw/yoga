package com.woniuxy.yogasystem.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.woniuxy.yogasystem.pojo.Coach;
import com.woniuxy.yogasystem.pojo.Request_Message;
import com.woniuxy.yogasystem.pojo.Trainee;
import com.woniuxy.yogasystem.pojo.Venues;
import com.woniuxy.yogasystem.provider.VenuesProvide;

public interface VenuesDao {
	@Select("select * from venues inner join coach_venues on coach_venues.vid=venues.id where cid=#{cid}")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="name",property="name"),
		@Result(column="phone",property="phone"),
		@Result(column="salary",property="salary"),
		@Result(column="descrie",property="descrie"),
		@Result(column="uid",property="uid"),
		@Result(column="img",property="img"),
		@Result(column="uid",property="address",one=@One(select="com.woniuxy.yogasystem.dao.AddressDao.findAddress"))
	})
	public List<Venues> findVenuesByCid(int cid);
	
	@Select("select * from venues where id=#{id}")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="name",property="name"),
		@Result(column="phone",property="phone"),
		@Result(column="salary",property="salary"),
		@Result(column="descrie",property="descrie"),
		@Result(column="uid",property="uid"),
		@Result(column="img",property="img"),
		@Result(column="uid",property="address",one=@One(select="com.woniuxy.yogasystem.dao.AddressDao.findAddress"))
	})
	public Venues findVenuesById(int id);
	
	@Select("select * from venues where uid=#{id}")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="name",property="name"),
		@Result(column="phone",property="phone"),
		@Result(column="salary",property="salary"),
		@Result(column="descrie",property="descrie"),
		@Result(column="uid",property="uid"),
		@Result(column="img",property="img"),
		@Result(column="uid",property="address",one=@One(select="com.woniuxy.yogasystem.dao.AddressDao.findAddress"))
	})
	public Venues findVenuesByUId(int id);
	
	@SelectProvider(type=VenuesProvide.class,method="findVenues")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="name",property="name"),
		@Result(column="addr",property="addr"),
		@Result(column="phone",property="phone"),
		@Result(column="salary",property="salary"),
		@Result(column="descrie",property="descrie"),
		@Result(column="uid",property="uid"),
		@Result(column="img",property="img"),
		@Result(column="uid",property="address",one=@One(select="com.woniuxy.yogasystem.dao.AddressDao.findAddress"))
	})
	public List<Venues> findVenues(Venues venues);
	
	//搜索学员功能
		@SelectProvider(type=VenuesProvide.class,method="findTraineeMsg")
		public List<Trainee> findManyTraineeMsg(Trainee trainee);
		
		//搜索教练功能
		@SelectProvider(type=VenuesProvide.class,method="findCoachMsg")
		public List<Coach> findManyCoachMsg(Coach coach);
		
		//查看我签约的学员
		@Select("select * from trainee inner join trainee_venues on trainee.id=trainee_venues.tid"
				+ " where vid=#{uid}")
		@Results({
			@Result(id=true,column="id",property="id"),
			@Result(column="img",property="img"),
			@Result(column="name",property="name"),
			@Result(column="phone",property="phone"),
			@Result(column="uid",property="address",one=@One(select="com.woniuxy.yogasystem.dao.AddressDao.findAddress")),
		})
		public List<Trainee> findSignTraineeMsg(int uid);
		
		//查看我签约的教练
		@Select("select * from coach inner join coach_venues on coach.id=coach_venues.cid"
				+ " where vid=#{uid}")
		public List<Coach> findSignCoachMsg(int uid);
		
		//签约教练：发送申请消息给教练 
		@Insert("insert into request_message(uid1,uid2,content,name,price,`character`,type,img)"
				+ " values(#{uid1},#{uid2},#{content},#{name},"
				+ "#{price},#{character},#{type},#{img})")		
		public void insertCoachMsg(Request_Message re);	
		
		//展示通知消息
		@Select("select content from request_message where uid2=#{uid} and type=0 and flag=0")
		public List<Request_Message> findMsgContent(int uid);
			
		//插入场馆接受教练通知消息
		@Insert("insert into request_message(uid1,uid2,content,name,character,type)"
				+ "values(#{uid1},#{uid2},#{content},#{name}," + "#{character},#{type})")
		public void insertReplyMsg(Request_Message re);
		
		//处理申请消息
		@Update("update request_message set flag=1 where id=#{id}")
		public void handleAllMsg(int id);
		
		//查询场馆具体信息
		@Select("select * from venues where uid=#{vid}")
		public Venues findVenuesMsg(int vid);
		
		@Select("select img from venues where uid=#{uid}")
		public String findHead(int uid);
		
		@Select("SELECT img FROM venues_img WHERE venuesid=#{otherUid} AND flag=0")
		List<String> findImgById(int otherUid);
}
