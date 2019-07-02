package com.woniuxy.yogasystem.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.FetchType;

import com.woniuxy.yogasystem.pojo.Circle_of_Friend;
import com.woniuxy.yogasystem.pojo.View;

public interface CircleDao {
	// 新增朋友圈
	@Insert("insert into circle_of_friends values(default,#{uid},#{name},#{title},#{content},#{time},#{img},0,#{head})")
	public void insertCircle(Circle_of_Friend circle);

	// 查看关注人的新消息
	@Select("SELECT * FROM circle_of_friends WHERE uid in(SELECT followuid uid FROM follows  WHERE uid=#{uid} and `status`!=0 )  ")
	@Results({ @Result(id = true, column = "id", property = "id"), @Result(column = "uid", property = "uid"),
			@Result(column = "name", property = "name"), @Result(column = "title", property = "title"),
			@Result(column = "content", property = "content"), @Result(column = "time", property = "time"),
			@Result(column = "img", property = "img"),
			@Result(column = "cid", property = "cid"),
			@Result(column = "time", property = "time"),
			@Result(column = "id", property = "list", many = @Many(select = "findView", fetchType = FetchType.EAGER
			)) })
	public List<Circle_of_Friend> findAllCircle(int uid);
	//查看朋友圈的评论
	@Select("select * from view_of_circle where cid=#{id} ORDER BY id DESC ")
	public List<View> findView(int id);
	// 查看自己朋友圈
		@Select("select * from circle_of_friends where uid=#{arg0}")
		public List<Circle_of_Friend> findMyCircle(int uid);
	// 查看自己朋友圈
	@Select("select * from circle_of_friends where uid=#{arg0} ORDER BY id DESC limit #{arg1},10")
	@Results({ @Result(id = true, column = "id", property = "id"), @Result(column = "uid", property = "uid"),
		@Result(column = "name", property = "name"), @Result(column = "title", property = "title"),
		@Result(column = "content", property = "content"), @Result(column = "time", property = "time"),
		@Result(column = "img", property = "img"),
		@Result(column = "cid", property = "cid"),
		@Result(column = "time", property = "time"),
		@Result(column = "id", property = "list", many = @Many(select = "findView", fetchType = FetchType.EAGER
		)) })
	public List<Circle_of_Friend> findMyTenCircle(int uid,int start);
	
	// 删除朋友圈
	@Update("update circle_of_friends set flag=1 where id=#{id}")
	public void deleteAd(int id);
	// 查看关注人的新消息10条
		@Select("SELECT * FROM circle_of_friends WHERE uid in(SELECT followuid uid FROM follows  WHERE uid=#{arg0} and `status`!=0 ) ORDER BY id DESC limit #{arg1},10  ")
		@Results({ @Result(id = true, column = "id", property = "id"), @Result(column = "uid", property = "uid"),
				@Result(column = "name", property = "name"), @Result(column = "title", property = "title"),
				@Result(column = "content", property = "content"), @Result(column = "time", property = "time"),
				@Result(column = "img", property = "img"),
				@Result(column = "cid", property = "cid"),
				@Result(column = "time", property = "time"),
				@Result(column = "id", property = "list", many = @Many(select = "findView", fetchType = FetchType.EAGER
				)) })
		public List<Circle_of_Friend> findTenCircle(int uid,int start);
	
}
