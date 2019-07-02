package com.woniuxy.yogasystem.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.woniuxy.yogasystem.pojo.Request_Message;

public interface MessageDao {
	
	@Select("select * from request_message  where uid2=#{param1} and type=#{param2} and request_message.flag=0")
	public List<Request_Message> findMessageById(int id,int type);
	
	@Select("select * from request_message where uid2=#{param1} and type=0 and request_message.flag=0")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="uid1",property="uid1"),
		@Result(column="uid2",property="uid2"),
		@Result(column="price",property="price"),
		@Result(column="vid",property="vid"),
		@Result(column="pid",property="pid"),
		@Result(column="content",property="content"),
		@Result(column="name",property="name"),
		@Result(column="img",property="img"),
		@Result(column="type",property="type"),
		@Result(column="character",property="character"),
		@Result(column="pid",property="private_Course",one=@One(select="com.woniuxy.yogasystem.dao.Private_CourseDao.findPrivate"))
	})
	public List<Request_Message> findResMessageById(int id);
	
	@Insert("insert into request_message(uid1,uid2,content,name,img,`character`,type) values(#{uid1},#{uid2},#{content},#{name},#{img},#{character},#{type}) ")
	public boolean insertMes(Request_Message message);
	
	@Update("update request_message set flag=1 where id=#{id} ")
	public boolean removeMes(int id);
	
	@Update("update request_message set flag=1 where pid=#{pid}")
	public boolean removeMesByPid(int pid);
	
}
