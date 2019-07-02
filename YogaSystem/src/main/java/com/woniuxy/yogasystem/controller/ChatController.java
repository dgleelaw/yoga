package com.woniuxy.yogasystem.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.woniuxy.yogasystem.pojo.Chat;
import com.woniuxy.yogasystem.pojo.UnReadChatMess;
import com.woniuxy.yogasystem.service.ChatService;
import com.woniuxy.yogasystem.util.WebSocketUtil;

@Controller
@RequestMapping("/chat")
public class ChatController {
	public int count = 0;
	@Resource
	private ChatService chatService;

	// 存储消息 0已读，1未读
	@RequestMapping("/one")
	@ResponseBody
	public void saveChatHaveRead(String message, String otherAcc, String mineAcc) {
		boolean state = WebSocketUtil.checkMessage(otherAcc);
		if (state) {
			int j = chatService.saveChatHaventRead(message, otherAcc, mineAcc);

		} else {
			int i = chatService.saveChatHaveRead(message, otherAcc, mineAcc);
		}

	}

	// 查询消息
	@RequestMapping("/select")
	@ResponseBody
	public List<Chat> selectChatMessage(String otherAcc, String mineAcc) {
		List<Chat> chat = new ArrayList<>();
		chat = chatService.selectChatMessage(otherAcc, mineAcc);
		return chat;
	}

	// 未读消息条数显示
	@RequestMapping("/countMessageNotRead")
	@ResponseBody
	public String countMessageN(HttpSession session) {
		int mineAcc=(int) session.getAttribute("uid");
		
		String message = null;
		
		message = count + "条未读消息";
		count = chatService.countMessageHaventRead(String.valueOf(mineAcc));
		message = count + "条未读消息";
		
		if (count != 0) {
			message = count + "条未读消息";
			
			/*chatService.setStateToRead(mineAcc);*/
			
		}
		return message;
	}

	// 未读消息查询
	@RequestMapping("/readFewMessage")
	@ResponseBody
	public List<UnReadChatMess> findMessageRecn(HttpSession session) {
		int mineAcc=(int) session.getAttribute("uid");
		ModelAndView mv=new ModelAndView();
		List<UnReadChatMess> chatre = new ArrayList<>();

		chatre = chatService.findMessageRecn(String.valueOf(mineAcc));		
		/*mv.addObject("readMessage", chatre);*/
		for (int i = 0; i < chatre.size(); i++) {
			System.out.println(chatre.get(i).getMessage());
		}
		chatService.setStateToRead(String.valueOf(mineAcc));

		return chatre;
	}
	
	
	// 未读消息变成已读
	@RequestMapping("/setStateToRead")
	@ResponseBody
	public void setStateToRead(String mineAcc) {
			chatService.setStateToRead(mineAcc);		
	}

}
