package com.woniuxy.yogasystem.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.woniuxy.yogasystem.pojo.Follows;

public interface FollowsDao {
	// 查询互相关注的人的uid
	@Select("SELECT a.uid uid,a.followuid followuid,a.name name,a.img img,a.character `character` FROM follows a INNER JOIN follows b on a.uid=b.followuid WHERE a.uid=#{uid} and b.uid=a.followuid and a.flag=0 and b.flag=0")
	public List<Follows> FindAllFollowsUidByOwnerUid(Integer uid);

	// 修改显示信息的权限,当页面修改权限时
	@Update("UPDATE follows SET `STATUS`=#{0} WHERE followuid=#{1};")
	public void UpdateStatus(int status, int followuid);
	
	//关注用户,即插入关注表
	@Insert("insert into follows values(default,#{uid},#{myname},#{myimg},#{followuid},#{name},#{img},#{character},#{status},0)")
	public void insertFollows(Follows follows);
	
/*	//根据followuid来建立follows对象
	@Select("SELECT c.uid followuid,c.name `name`,c.img img,u.role `character`,c.infostatus `status` FROM `user` u INNER JOIN coach c on u.id=c.uid where u.id=#{followuid}")
	public Follows bulidFollows(Integer followuid);*/
	//查询关注了的人的uid
	@Select("select * from follows where uid=#{uid} and flag=0")
	public List<Follows> findAllFollows(int uid);
	//取消关注
	@Update("update follows set flag=1 where uid=#{uid} and followuid=#{followuid}")
	public void deleteFollows(Follows follows);
	//关注我的人
	@Select("select * from follows where followuid=#{uid} AND flag=0 ")
	public List<Follows> findMyFollows(int uid);
	
	
	
	@Select("SELECT  followuid FROM follows WHERE uid=#{uid} AND flag=0")
	int[] findFollowidByUid(int uid);
}
