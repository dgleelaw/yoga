package com.woniuxy.yogasystem.dao;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.woniuxy.yogasystem.pojo.Private_Course;

public interface Private_CourseDao {
	
	@Select("select * from private_course where id=#{id} and flag=0")
	public Private_Course findPrivate(int id);
	
	@Update("update private_course set flag=1,tid=#{param2} where id=#{param1}")
	public boolean remove(int id,int tid);
}
