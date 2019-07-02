package com.woniuxy.yogasystem.dao;

import com.woniuxy.yogasystem.pojo.*;
import com.woniuxy.yogasystem.provider.CourseProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface PublicCourseDao {
    /**
     *新增公开课
     */
    @Insert("insert into public_course (vid,cid,price,starttime) values(#{vid},#{cid},#{price},#{startTime})")
    public void insertPublicCourse(Public_Course course);

    /**
     * 查询公开课程
     */
    @SelectProvider(type = CourseProvider.class,method = "selectPublic")
    public List<Public_Course> selectCourse(Public_Course course);

    /**
     * 删除私教课程
     * @param id
     */
    @Update("update public_course set flag=1 where id=#{id}")
    public void deletePublic(Integer id);

    /**
     * 查询出教练、学员、场馆姓名
     * @param id
     * @return
     */
    @Select("select * from venues where id=#{id}")
    public Venues selectVname(Integer id);
    @Select("select * from trainee where id=#{id}")
    public Trainee selectTname(Integer id);
    @Select("select * from coach where id=#{id}")
    public Coach selectCname(Integer id);

}
