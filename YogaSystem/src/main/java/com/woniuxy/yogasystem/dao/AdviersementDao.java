package com.woniuxy.yogasystem.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.woniuxy.yogasystem.pojo.Adviersement;

public interface AdviersementDao {
	// 插入广告
	@Insert("insert into adviersement values(0,default,#{title},#{content},#{img})")
	public void insertAd(Adviersement ad);

	// 删除广告
	@Update("update adviersement set flag=1 where id=#{id}")
	public void deleteAd(int id);

	// 查询广告
	@Select("select * from adviersement where flag=0")
	public List<Adviersement> findAllAd();
}
