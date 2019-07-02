package com.woniuxy.yogasystem.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import com.woniuxy.yogasystem.pojo.Coach;
import com.woniuxy.yogasystem.pojo.Order_Form;
import com.woniuxy.yogasystem.pojo.Private_Course;
import com.woniuxy.yogasystem.pojo.Request_Message;
import com.woniuxy.yogasystem.pojo.Trainee;
import com.woniuxy.yogasystem.pojo.Venues;
import com.woniuxy.yogasystem.provider.TraineeProvider;

public interface TraineeDao {

	@Select("select * from trainee inner join trainee_coach on trainee_coach.tid=trainee.id where cid=#{cid}")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="name",property="name"),
		@Result(column="phone",property="phone"),
		@Result(column="img",property="img"),
		@Result(column="uid",property="address",one=@One(select="com.woniuxy.yogasystem.dao.AddressDao.findAddress")),
	})
	public List<Trainee> findTraineeByCid(int cid);

	@Select("select * from trainee where id=#{id} and flag=0")
	public Trainee findTraineeById(int id);

	@Select("select * from trainee where uid=#{id} and flag=0")
	public Trainee findTraineeByUId(int id);

	// 查看教练的基本信息
	@Select("select name,img,shool from coach where uid=#{uid} and flag=0")
	public Coach findCoachBaiscMsg(int uid);
	// 查看教练的详细信息
	@Select("select * from coach where flag=0 and uid=#{uid}")
	@Results({ @Result(id = true, column = "id", property = "id"), @Result(column = "phone", property = "phone"),
			@Result(column = "infostatus", property = "infostatus"), @Result(column = "addr", property = "addr"),
			@Result(column = "school", property = "school"),
			@Result(column = "uid", property = "uid"),
			@Result(column = "authentication", property = "authentication"),
			@Result(column = "name", property = "name"), @Result(column = "img", property = "img"),
			@Result(column = "privatetime", property = "privatetime"), @Result(column = "uid", property = "uid"),
			@Result(column = "uid", property = "venues", many = @Many(select = "com.woniuxy.yogasystem.dao.Coach_VenuesDao.findVenuesDetailMsg")),
			@Result(column="uid",property="address",one=@One(select="com.woniuxy.yogasystem.dao.AddressDao.findAddress"))
	})
			
	public Coach findCoachDetailMsg(int uid);

	// 查看场馆的基本信息
	@Select("select name,img,addr from venues where flag=0 and uid=#{uid}")
	public Venues findVenuesBaiscMsg(int uid);

	// 查看场馆的详细信息
	@Select("select * from venues where flag=0")
	@Results({ @Result(id = true, column = "uid", property = "uid"), @Result(column = "phone", property = "phone"),
			@Result(column = "addr", property = "addr"), @Result(column = "name", property = "name"),
			@Result(column = "img", property = "img"), @Result(column = "detail", property = "detail"),
			@Result(column = "price", property = "price"),
			@Result(column = "uid", property = "coach", many = @Many(select = "com.woniuxy.dao.Coach_VenuesDao.findCoachDetailMsg")) })
	public Venues findVenuesDetailMsg(int uid);

	// 搜索教练功能
	// 根据条件教练的详细信息=条件检索：姓名，流派，认证，id
	@SelectProvider(type = TraineeProvider.class, method = "findCoachMsg")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="id",property="id"),
		@Result(column="uid",property="uid"),
		@Result(column="name",property="name"),
		@Result(column="img",property="img"),
		@Result(column="school",property="school"),
		@Result(column="uid",property="address",one=@One(select="com.woniuxy.yogasystem.dao.AddressDao.findAddress")),
		
	})
	public List<Coach> findCoachMsg(Coach coach);
	// 搜索场馆功能
	// 根据条件场馆的详细信息=条件检索：姓名，地址
	@SelectProvider(type = TraineeProvider.class, method = "findVenuesMsg")
	public List<Venues> findVenues(Venues venues);

	// 查询我的教练
	@Select("select * from trainee_coach inner join coach on coach.id=trainee_coach.cid WHERE tid=#{uid}")
	public List<Coach> findMyCoachMsg(int uid);

	// 查询我的场馆
	@Select("select * from trainee_venues inner join venues on venues.id=trainee_venues.vid" + " WHERE tid=#{uid}")
	@Results({ @Result(id = true, column = "id", property = "id"), 
				@Result(column = "phone", property = "phone"),
			    @Result(column = "img", property = "img"),
				@Result(column = "name", property = "name"),
				@Result(column="uid",property="address",one=@One(select="com.woniuxy.yogasystem.dao.AddressDao.findAddress"))
			})
	public List<Venues> findMyVenuesMsg(int uid);

	// 学员邀请教练信息插入消息表中
	@Insert("insert into request_message(uid1,uid2,content,`name`,img,pid,price,`character`,type,vid)"
			+ " values(#{uid1},#{uid2},#{content},#{name},#{img},#{pid}," + "#{price},#{character},#{type},#{vid})")
	public void insertCoachMsg(Request_Message rm);

	// 学员邀请场馆信息插入消息表中
	@Insert("insert into request_message(uid1,uid2,content,name,img,price,`character`,type)"
			+ " values(#{uid1},#{uid2},#{content},#{name},#{img}," + "#{price},#{character},#{type})")
	public void insertVenuesMsg(Request_Message rm);

	// 查看通知消息
	@Select("select content from request_message where uid2=#{uid} and type=1 and flag=0")
	public List<Request_Message> findMsgContent(int uid);

	// 根据学员id查询学员钱包余额
	@Select("select money from moneybag where uid=#{traineeId} and flag=0")
	public int selectBalance(int traineeId);

	// 查看我的订单
	@Select("select * from order_form where tid=#{uid}")
	public List<Order_Form> findTraineeOrder(int uid);

	@Select("select * from private_course where cid=#{uid}")
	@Results({ @Result(id = true, column = "id", property = "id"), @Result(column = "cid", property = "cid"),
			@Result(column = "price", property = "price"), @Result(column = "starttime", property = "startTime"),
			@Result(column = "vid", property = "venues", many = @Many(select = "com.woniuxy.yogasystem.dao.VenuesDao.findVenuesById")) })
	// 查看教练课程信息
	public List<Private_Course> findCoachCourse(int uid);

	@Select("select img from trainee where uid=#{uid}")
	public String findHead(int uid);

	
}
