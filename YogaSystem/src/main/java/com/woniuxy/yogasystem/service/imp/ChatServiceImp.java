package com.woniuxy.yogasystem.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.Resource;
import javax.lang.model.element.Element;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.woniuxy.yogasystem.dao.ChatDao;
import com.woniuxy.yogasystem.pojo.Chat;
import com.woniuxy.yogasystem.pojo.UnReadChatMess;
import com.woniuxy.yogasystem.service.ChatService;
@Transactional
@Service("chatService")
public class ChatServiceImp implements ChatService {
	@Resource
	private ChatDao chatDao;

	// 存储已读消息
	@Override
	public int saveChatHaveRead(String message, String otherAcc, String mineAcc) {
		int oId = Integer.parseInt(otherAcc);
		int mId = Integer.parseInt(mineAcc);
		int i = 0;
		i = chatDao.saveChatHaveRead(message, oId, mId);

		return i;

	}

	// 查询记录
	@Override
	public List<Chat> selectChatMessage(String otherAcc, String mineAcc) {
		List<Chat> chat = new ArrayList<>();
		int oId = Integer.parseInt(otherAcc);
		int mId = Integer.parseInt(mineAcc);
		chat = chatDao.selectChatMessage(oId, mId);
		return chat;

	}

	// 查询未读
	@Override
	public List<UnReadChatMess> findMessageRecn(String mineAcc) {
		List<UnReadChatMess> list = new ArrayList<>();
		
		List<String> chatMessage = new ArrayList<>();
		int[] unReadId = null;
		int mId = Integer.parseInt(mineAcc);
		// 我未读消息的ID
		unReadId = chatDao.findIdSendToMeUnread(mId);
		// 去重
		Set<Integer> set = new TreeSet<>();
		for (int i = 0; i < unReadId.length; ++i) {
			set.add(unReadId[i]);
		}
		// 去重后的数组
		int[] unRead = new int[set.size()];
		int i = 0;
		for (Integer integer : set) {
			unRead[i] = integer;
			++i;
		}
		for (int j = 0; j < unRead.length; j++) {
			UnReadChatMess chatMess = new UnReadChatMess();
			chatMessage = chatDao.findMessageRecn(unRead[j]);
			chatMess.setMid(mId);
			chatMess.setYid(unRead[j]);
			chatMess.setMessage(chatMessage);
			list.add(chatMess);
		}
		
		return list;
	}

	// 查询未读条数
	@Override
	public int countMessageHaventRead(String mineAcc) {
		int count = 0;
		/* int oId = Integer.parseInt(otherAcc); */
		int mId = Integer.parseInt(mineAcc);
		count = chatDao.countMessageHaventRead(mId);
		return count;
	}

	// 将未读修改成已读
	@Override
	public void setStateToRead(String mineAcc) {

		int mId = Integer.parseInt(mineAcc);
		chatDao.setStateToRead(mId);

	}

	// 存储未读
	@Override
	public int saveChatHaventRead(String message, String otherAcc, String mineAcc) {
		int oId = Integer.parseInt(otherAcc);
		int mId = Integer.parseInt(mineAcc);
		int i = 0;
		i = chatDao.saveChatHaventRead(message, oId, mId);

		return i;
	}

}
