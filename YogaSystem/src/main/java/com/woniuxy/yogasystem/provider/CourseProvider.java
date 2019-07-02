package com.woniuxy.yogasystem.provider;

import com.woniuxy.yogasystem.pojo.Private_Course;
import com.woniuxy.yogasystem.pojo.Public_Course;
import org.apache.ibatis.jdbc.SQL;


public class CourseProvider {
    public String selectPublic(Public_Course course){
        SQL sql = new SQL().SELECT("*").FROM("public_course");
        if(course.getVid()>0){
            sql.WHERE("vid="+course.getVid());
        }

        if(course.getCid()>0){
            sql.WHERE("cid="+course.getCid());
        }
        if(course.getPrice()>0){
            sql.WHERE("vid="+course.getVid());
        }
        if(course.getStartTime()!=null&& course.getStartTime().length()>0){
            sql.WHERE("starttime='"+course.getStartTime()+"'");
        }
        if(course.getEndTime()!=null&&course.getEndTime().length()>0){
            sql.WHERE("endtime='"+course.getEndTime()+"'");
        }
        if(course.getId()>0){
            sql.WHERE("id="+course.getId());
        }
        sql.WHERE("flag=0");
        return sql.toString();
    }
    public String selectPrivate(Private_Course course){
       SQL sql=new SQL().SELECT("*").FROM("private_course");
       if(course.getStartTime()!=null&&course.getStartTime().length()>0){
           sql.WHERE("starttime='"+course.getStartTime()+"'");
       }
        if(course.getVid()>0){
            sql.WHERE("vid="+course.getVid());
        }
        if(course.getCid()>0){
            sql.WHERE("cid="+course.getCid());
        }
        if(course.getTid()>0){
            sql.WHERE("tid="+course.getTid());
        }
       sql.WHERE("flag=0");
       return sql.toString();
    }
}
