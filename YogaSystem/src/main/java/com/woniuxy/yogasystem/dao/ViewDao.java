package com.woniuxy.yogasystem.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.woniuxy.yogasystem.pojo.View;

public interface ViewDao {
	//插入评论
@Insert("insert into view_of_circle values(default,#{cid},#{text},#{img},#{name},#{time})")
public void insertView(View view);





@Select("SELECT img FROM venues_img WHERE venuesid=#{otherUid} AND flag=0")
List<String> findImgById(int otherUid);
}
