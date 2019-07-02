package com.woniuxy.yogasystem.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.woniuxy.yogasystem.pojo.Coach;
import com.woniuxy.yogasystem.pojo.Order_Form;
import com.woniuxy.yogasystem.pojo.Trainee;
import com.woniuxy.yogasystem.pojo.Venues;

public interface OrderDao {

	@Insert("insert into order_form(tid,cid,price,starttime,vid,number) values(#{tid},#{cid},#{price},#{startTime},#{vid},#{number})")
	public boolean addOrder(Order_Form form);

	// 查看学员订单详细信息
	@Select("select * from order_form where flag=0 and tid=#{id}")
	@Results({ @Result(id = true, column = "id", property = "id"), @Result(column = "price", property = "price"),
			@Result(column = "starttime", property = "startTime"), @Result(column = "number", property = "number"),
			@Result(column = "status", property = "status"),
			@Result(column = "cid", property = "coach", one = @One(select = "findCoachMsgById")),
			@Result(column = "vid", property = "venues", one = @One(select = "com.woniuxy.yogasystem.dao.VenuesDao.findVenuesById")) })
	public List<Order_Form> findOrderMsg(int id);

	// 根据cid查看，教练姓名，教练流派，教练电话，
	@Select("select name,school,phone,uid from coach where id=#{cid} and flag=0")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="uid",property="uid"),
		@Result(column="name",property="name"),
		@Result(column="school",property="school"),
		@Result(column="phone",property="phone")
	})
	public Coach findCoachMsgById(int cid);

	// 根据vid查看，，场馆名称，场馆地址
	@Select("select name,addr from venues where id=#{vid} and flag=0")
	public Venues findVenuesMsgById(int vid);

	// 根据学员表查出学员id
	@Select("select id from trainee where uid=#{uid} and flag=0")
	public int findTraineeById(int uid);

	// 查询余额
	@Select("select money from moneybag where uid=#{uid}")
	public int showMoney(int uid);

	// 充值
	@Update("update moneybag set money=money+#{param2} where uid=#{param1} and flag=0")
	public void updateMoney(int uid, double d);

	@Update("update order_form set status=2 where number=#{number}")
	// 支付，根据订单编号改变状态
	public void updateStatus(String number);

	// 删除订单
	@Update("update order_form set flag=1 where number=#{number}")
	public void updateFlag(String number);

	// 查找用户角色
	@Select("select role from user where id=#{uid} and flag=0")
	public int findRole(int uid);

	// 教练订单详细信息
	@Select("select * from order_form where flag=0 and cid=#{id}")
	@Results({ @Result(id = true, column = "id", property = "id"), @Result(column = "price", property = "price"),
			@Result(column = "starttime", property = "startTime"), @Result(column = "number", property = "number"),
			@Result(column = "status", property = "status"),
			@Result(column = "tid", property = "trainee", one = @One(select = "findTraineeMsgById")),
			@Result(column = "vid", property = "venues", one = @One(select = "com.woniuxy.yogasystem.dao.VenuesDao.findVenuesById")) })
	public List<Order_Form> findCoachOrderMsg(int id);

	// 根据tid查看，学员姓名，学员的电话,学员地址
	@Select("select * from trainee where id=#{tid} and flag=0")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="name",property="name"),
		@Result(column="phone",property="phone"),
		@Result(column="uid",property="address",one=@One(select="com.woniuxy.yogasystem.dao.AddressDao.findAddress")),
	})
	public Trainee findTraineeMsgById(int tid);

	
	@Update("update order_form set status=1 where number=#{number}")
	public void updateOrder(String number);

}
