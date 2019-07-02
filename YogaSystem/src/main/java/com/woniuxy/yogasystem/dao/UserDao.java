package com.woniuxy.yogasystem.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.woniuxy.yogasystem.pojo.Address;
import com.woniuxy.yogasystem.pojo.Apply;
import com.woniuxy.yogasystem.pojo.ApplyMessage;
import com.woniuxy.yogasystem.pojo.Coach;
import com.woniuxy.yogasystem.pojo.Trainee;
import com.woniuxy.yogasystem.pojo.User;
import com.woniuxy.yogasystem.pojo.Venues;

public interface UserDao {
	/*
	 * 公共操作
	 */
	// 查询用户账户是否存在
	@Select("select * from user where acc=#{acc}")
	public User checkacc(String acc);

	// 注册信息
	@Insert("insert into user(acc,pwd,role,flag) values (#{acc},#{pwd},5,0)")
	public boolean register(User user);

	// 通过账户查密码
	@Select("select pwd from user where acc=#{acc}")
	public String findPwdByAcc(User user);

	// 查询所有用户的信息
	@Select("select * from user where acc=#{acc}")
	public User checkMessageByAcc(String acc);

	// 重置密码
	@Update("update user set pwd=#{pwd} where acc=#{acc}")
	public int reset(User user);

	// 插入地址
	@Insert("insert into address (province,city,county,town,detail,uid)values(#{province},#{city},#{county},#{town},#{detail},#{uid})")
	public boolean insertAdd(Address address);

	// 分配角色
	@Update("update user set role =#{arg0} where id=#{arg1}")
	public int roleupdate(int role, int uid);

	// 更新地址
	@Update("update address set province=#{param1.province},city=#{param1.city},county=#{param1.county},town=#{param1.town},detail=#{param1.detail} where uid=#{param2}")
	public int updateAdd(Address address, int uid);

	/*
	 * 超级管理员操作 0学院 1教练 2场馆 3管理 4超管 5游客
	 */
	// 注册管理员
	@Insert("insert into user (acc,pwd,role,flag) values(#{acc},#{pwd},3,0)")
	public boolean regManager(User user);

	/*
	 * 管理员操作
	 */
	// 查询applymessage里所有教练申请请求
	@Select("select * from applymessage where type=1 and flag=0")
	public List<ApplyMessage> findCoachApply();

	// 查询applymessage里所有场馆申请请求
	@Select("select * from applymessage where type=0 and flag=0")
	public List<ApplyMessage> findVenuesApply();

	// 找出申请的所有信息
	@Select("select * from apply where uid=#{uid} and flag=0")
	public Apply findMes(int uid);

	// 把信息插入到教练表里
	@Insert("insert into coach(uid,phone,infostatus,name,flag,sex,img,authentication,privatetime,salary,school,authstatus,vx,vy) values(#{uid},#{phone},#{infostatus},#{name},#{flag},#{sex},#{img},0,#{privatetime},#{salary},#{school},#{authstatus},#{vx},#{vy}) ")
	public boolean addCoach(Apply apply);

	// 把申请信息从申请表软删除
	@Update("update applymessage set flag=1 where uid=#{uid}")
	public int deleteMes(int uid);

	// 从申请applp表软删除
	@Update("update apply set flag=1 where uid=#{uid}")
	public int deleteApply(int uid);

	// 地址硬删除
	@Delete("delete  from address where uid=#{uid}")
	public int deleteAdd(int uid);

	// 图片硬删
	@Delete("delete  from venues_img where venuesid=#{uid}")
	public int deleteVenuesPicture(int uid);

	// 把信息插入到场馆表里
	@Insert("insert into venues (uid,img,name,phone,descrie,flag,salary,vx,vy) values (#{uid},#{img},#{name},#{phone},#{descrie},#{flag},#{salary},#{vx},#{vy})")
	public boolean addVenues(Apply apply);

	/*
	 * 对学员的一系列操作
	 */

	// 填写资料时给学员一个默认的手机号 uid 状态 名字
	@Insert("insert into trainee(uid,phone,status,name,flag,sex,img,vx,vy) values(#{id},#{acc},0,#{acc},0,1,'/headimg/yuga.jpg',0,0)")
	public boolean insertdefaultByUid(User user);

	// 更新 用户现在已有的信息
	@Update("update trainee set sex=#{sex},img=#{img}, phone=#{phone},status=#{status},name=#{name},vx=#{vx},vy=#{vy} where uid=#{uid}")
	public int updateDefault(Trainee trainee);

	@Select("select * from trainee where uid=#{uid}")
	public Trainee findTrainee(int uid);

	@Select("select * from address where uid=#{uid}")
	public Address findTraineeAdd(int uid);

	/*
	 * 对教练的一系列操作
	 */

	// 填写资料时给教练一个默认的手机号 uid 状态 名字 性别 头像 认证 是否任课 薪资
	@Insert("insert into coach(uid,phone,infostatus,name,flag,sex,img,authentication,privatetime,salary,school,authstatus) values(#{id},#{acc},0,#{acc},0,1,'/headimg/yuga.jpg',0,0,0,' ','  ')")
	public boolean insertCoachdefaultByUid(User user);

	// 更新 教练现在已有的信息
	@Update("update coach set sex=#{sex},img=#{img}, phone=#{phone},infostatus=#{infostatus},name=#{name},privatetime=#{privatetime},salary=#{salary},school=#{school},authstatus=#{authstatus},vx=#{vx},vy=#{vy} where uid=#{uid}")
	public int updateCoachDefault(Coach coach);

	// 查询教练所有信息
	@Select("select * from coach where uid=#{uid}")
	public Coach findCoach(int uid);

	@Select("select * from address where uid=#{uid}")
	public Address findCochAdd(int uid);

	// 把申请教练信息添加到临时申请表里
	@Insert("insert into apply (uid,phone,infostatus,name,flag,sex,img,authentication,privatetime,salary,school,authstatus,vx,vy) values(#{uid},#{phone},#{infostatus},#{name},0,#{sex},#{img},#{authentication},#{privatetime},#{salary},#{school},#{authstatus},#{vx},#{vy})")
	public boolean applyCoach(Coach coach);

	// 把申请信息放入applymessage内 type教练是1
	@Insert("insert into applymessage (uid,phone,name,img,salary,school,sex,authstatus,type,flag) values(#{uid},#{phone},#{name},#{img},#{salary},#{school},#{sex},#{authstatus},1,0)")
	public boolean applyCoachMes(Coach coach);

	/*
	 * 对场馆的一系列操作
	 * 
	 */
	// 查询场馆是否被注册
	@Select("select * from venues where name=#{name}")
	public Venues checkName(String name);

	// 查询场馆是否被申请注册
	@Select("select * from applymessage where name=#{name}")
	public ApplyMessage checkNameFromApply(String name);

	// 填写资料时给场馆一个默认的手机号 uid 名字 性 头像 认证 薪资 描述
	@Insert("insert into venues (uid,img,name,phone,descrie,flag,salary) values (#{id},'/headimg/yuga.jpg',#{acc},#{acc},' ',0,0)")
	public boolean insertVenuesdefaultByUid(User user);

	// 更新场馆已有的信息
	@Update("update venues set img=#{img},name=#{name},phone=#{phone},descrie=#{descrie},salary=#{salary},vx=#{vx},vy=#{vy} where uid=#{uid}")
	public int updateVenuesDefault(Venues venues);

	// 插入场馆图片
	@Insert("insert into venues_img (venuesid,img,flag) values(#{arg1},#{arg0},0)")
	public boolean insertVenusePic(String pic, int venuesid);

	// 查询场馆信息
	@Select("select * from venues where uid=#{uid}")
	public Venues findVenues(int uid);

	// 查询场馆地址
	@Select("select * from address where uid=#{uid}")
	public Address findVenuesAdd(int uid);

	// 软删除场馆图片
	@Update("update venues_img set flag=1 where venuesid=#{uid}")
	public int deleteVenuesPic(int uid);

	// 把申请场馆信息添加到临时申请表里
	@Insert("insert into apply (uid,phone,name,flag,img,salary,vx,vy,descrie) values(#{uid},#{phone},#{name},0,#{img},#{salary},#{vx},#{vy},#{descrie})")
	public boolean applyVenues(Venues venues);

	// 把申请信息放入applymessage内 type场馆是0
	@Insert("insert into applymessage (uid,phone,name,img,salary,type,flag,descrie) values(#{uid},#{phone},#{name},#{img},#{salary},0,0,#{descrie})")
	public boolean applyVenuesMes(Venues venues);
	
	
	@Select("select * from trainee where uid=#{uid}")
    public Trainee selectTrainee(Integer uid);

    @Select("select * from coach")
    public List<Coach> selectAllCoach();

    @Select("select * from coach where uid=#{uid}")
    public Coach selectCoach(Integer uid);

    @Select("select * from venues")
    public List<Venues> selectAllVenues();

    @Select("select * from venues where uid=#{uid}")
    public Venues selectVenue(Integer uid);

    
    @Select("select * from user where id=#{uid}")
    public User findUserByUid(int uid);
    @Select("select * from coach where uid=#{uid}")
    public Coach findCoachByUid(int uid);
    @Select("select * from trainee where uid=#{uid}")
    public Trainee findTraineeByUid(int uid);
    @Select("select * from venues where uid=#{uid}")
    public Venues findVenuesByUid(int uid);
}
