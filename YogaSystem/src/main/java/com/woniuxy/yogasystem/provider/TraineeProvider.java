package com.woniuxy.yogasystem.provider;

import org.apache.ibatis.jdbc.SQL;

import com.woniuxy.yogasystem.pojo.Coach;
import com.woniuxy.yogasystem.pojo.Venues;

public class TraineeProvider {
	//查询
	//select * from coach where
	//authentication=#{authentication}认证 and name=#{name} 姓名
	//and school=#{school}//流派
	public String findCoachMsg(Coach coach){
		SQL sql = new SQL().SELECT("*").FROM("coach");
		if(coach.getName()!=null&& coach.getName().length()!=0){
			sql.WHERE("name like '%"+coach.getName()+"%'");
		}
		if(coach.getSchool()!=null&& coach.getSchool().length()!=0){
			sql.WHERE("school like '%"+coach.getSchool()+"%'");
		}
		if(coach.getAuthentication()>0){
			sql.WHERE("authentication="+coach.getAuthentication());
		}
		if(coach.getUid()>0){
			sql.WHERE("uid="+coach.getUid());
		}
		return sql.toString();
	}
	//查询场馆信息
	//select * from venues where name=#{name} and addr=#{addr}
	public String findVenuesMsg(Venues venues){
		SQL sql = new SQL().SELECT("*").FROM("venues");
		if(venues.getName()!=null&&venues.getName().length()!=0){
			sql.WHERE("name like '%"+venues.getName()+"%'");
		}
		if(venues.getAddr()!=null&&venues.getAddr().length()!=0){
			sql.WHERE("addr like '%"+venues.getAddr()+"%'");
		}
		if(venues.getDescrie()!=null&&venues.getAddr().length()!=0){
			sql.WHERE("detail like '%"+venues.getDescrie()+"%'");
		}
		if(venues.getUid()>0){
			sql.WHERE("uid="+venues.getUid());
		}
		return sql.toString();
	}
	
}
