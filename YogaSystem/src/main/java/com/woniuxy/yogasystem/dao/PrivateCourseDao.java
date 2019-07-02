package com.woniuxy.yogasystem.dao;

import com.woniuxy.yogasystem.pojo.Order_Form;
import com.woniuxy.yogasystem.pojo.Private_Course;
import com.woniuxy.yogasystem.provider.CourseProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface PrivateCourseDao {
    /**
     * 教练发布私教课
     */
    @Insert("insert into private_course (vid,cid,price,starttime) values(#{vid},#{cid},#{price},#{startTime})")
    public void insertPrivateCourse(Private_Course course);

    /**
     * 发布私教课 0，私教课确认 1（课程不再显示），学员签到 2，教练签到 3，付款订单完成 4
     */
    @Update("update private_course set flag=#{flag} where id=#{id}")
    public void completeOrder(Private_Course course);

    /**
     * 查询私教课程
     */
    @SelectProvider(type = CourseProvider.class,method = "selectPrivate")
    public List<Private_Course> selectPrivateCourse(Private_Course course);

    /**
     * 删除私教课程
     * @param id
     */
    @Update("update private_course set flag=1 where id=#{id}")
    public void deletePrivate(Integer id);
}
