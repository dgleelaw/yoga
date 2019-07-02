package com.woniuxy.yogasystem.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import com.woniuxy.yogasystem.pojo.Chat;

public interface ChatDao {
	// 存储已读消息 state 0已读 ，1未读
	@Insert("INSERT INTO chat(`mid`,yid,chatmessage,state) VALUES(#{arg2},#{arg1},#{arg0},0)")
	int saveChatHaveRead(String message, int otherAcc, int mineAcc);

	// 存储未读消息 state 0已读 ，1未读
	@Insert("INSERT INTO chat(`mid`,yid,chatmessage,state) VALUES(#{arg2},#{arg1},#{arg0},1)")
	int saveChatHaventRead(String message, int otherAcc, int mineAcc);

	// 查询记录
	@Select("SELECT * FROM chat WHERE (mid=#{arg1} AND yid=#{arg0}) OR (mid=#{arg0} AND yid=#{arg1});")
	List<Chat> selectChatMessage(int otherAcc, int mineAcc);

	// 查询未读 0已读 ，1未读
	@Select("SELECT chatmessage FROM chat WHERE mid=#{otherAcc} AND state=1")
	List<String> findMessageRecn(int otherAcc);

	// 查询未读条数
	@Select("SELECT COUNT(chatmessage) FROM chat WHERE yid=#{mineAcc} AND state=1;")
	int countMessageHaventRead(int mineAcc);

	// 修改未读成为已读
	@Update("UPDATE chat SET state=0 WHERE yid=#{mineAcc}")
	void setStateToRead(int mineAcc);

	// 我未读消息的ID
	@Select("SELECT mid FROM chat WHERE yid=#{mId} AND state=1")
	int[] findIdSendToMeUnread(int mId);

}
