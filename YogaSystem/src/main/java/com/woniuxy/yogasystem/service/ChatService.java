package com.woniuxy.yogasystem.service;

import java.util.List;

import com.woniuxy.yogasystem.pojo.Chat;
import com.woniuxy.yogasystem.pojo.UnReadChatMess;

public interface ChatService {
	// 存储已读消息
		int saveChatHaveRead(String message, String otherAcc, String mineAcc);

		List<Chat> selectChatMessage(String otherAcc, String mineAcc);

		List<UnReadChatMess> findMessageRecn(String mineAcc);

		int countMessageHaventRead(String mineAcc);

		void setStateToRead(String mineAcc);
//存储未读
		int saveChatHaventRead(String message, String otherAcc, String mineAcc);

}
