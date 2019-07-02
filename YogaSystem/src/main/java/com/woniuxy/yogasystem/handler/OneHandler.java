package com.woniuxy.yogasystem.handler;

import java.io.IOException;

import javax.annotation.Resource;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.RestController;

import com.woniuxy.yogasystem.service.ChatService;
import com.woniuxy.yogasystem.util.WebSocketUtil;
/* 一对一对话
 * 
 * @author Administrator
 *
 */
@RestController
@ServerEndpoint("/OneHandler/{userName}/{userOtherAcc}/{userMineAcc}") // 表示接受的是STOMP协议提
public class OneHandler{
	
	// 建立连接
	@OnOpen
	public void openSession(@PathParam("userName") String userName, @PathParam("userOtherAcc") String userOtherAcc,
			@PathParam("userMineAcc") String userMineAcc, Session session) {

		

		// 消息
		String message = "欢迎:" + userName + "加入群聊";
		// 加入聊天室
		// WebSocketUtil.MESSAGEMAP.put(userName, session);
		if (WebSocketUtil.ONETOONEMAP.get(userMineAcc) == null) {
			WebSocketUtil.ONETOONEMAP.put(userMineAcc, session);

		} else {
			if (WebSocketUtil.ONETOONEMAP.get(userOtherAcc) == null) {
				WebSocketUtil.ONETOONEMAP.put(userOtherAcc, session);
			} else {				
					WebSocketUtil.sendMessageToOne(message, userOtherAcc, userMineAcc);
			}
		}

		// 发送消息
		// WebSocketUtil.sendMessageToAll(message);

	}

	@OnMessage
	public void onMessage(@PathParam("userName") String userName, @PathParam("userOtherAcc") String userOtherAcc,
			@PathParam("userMineAcc") String userMineAcc, String message) {
	
		message = userName + ":" + message;
	
			WebSocketUtil.sendMessageToOne(message, userOtherAcc, userMineAcc);
	
		

	}

	// 离开聊天室
	@OnClose
	public void onClose(@PathParam("userName") String userName, @PathParam("userOtherAcc") String userOtherAcc,
			@PathParam("userMineAcc") String userMineAcc, Session session) {
		// 将当前用户从map中移除 注销
		WebSocketUtil.ONETOONEMAP.remove(userMineAcc);
		// 群发消息
		WebSocketUtil.sendMessageToAll("用户:" + userName + "离开聊天室", userOtherAcc, userMineAcc);
		// 关闭session
		try {
			session.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 连接异常
	@OnError
	public void onError(Session session, Throwable throwable) {
		try {
			session.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

//	@Override
//	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//		if (Handler2.applicationContext==null) {
//			Handler2.applicationContext = applicationContext;
//		}
//	}
}
