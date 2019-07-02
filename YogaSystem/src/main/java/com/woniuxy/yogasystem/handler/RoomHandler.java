package com.woniuxy.yogasystem.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.springframework.web.bind.annotation.RestController;

import com.woniuxy.yogasystem.util.WebSocketUtil;

@RestController
@ServerEndpoint("/RoomHandler/{userName}/{roomAcc}/{userMineAcc}") // 表示接受的是STOMP协议提
public class RoomHandler {
	public List<String> roomAccounts = null;

	// 建立连接
	@OnOpen
	public void openSession(@PathParam("userName") String userName, @PathParam("roomAcc") String roomAcc,
			@PathParam("userMineAcc") String userMineAcc, Session session) {

		// 消息
		String message = "欢迎:" + userName + "加入群聊";

		// 加入聊天室
		switch (roomAcc) {
		case "6":
			WebSocketUtil.MESSAGEMAPGROUP1.put(userMineAcc, session);
			break;
		case "22":
			WebSocketUtil.MESSAGEMAPGROUP2.put(userMineAcc, session);
			break;
		default:
			break;
		}

			// 发送消息
			WebSocketUtil.sendMessageToAll(message, roomAcc, userMineAcc);
		}

	

	@OnMessage
	public void onMessage(@PathParam("userName") String userName, @PathParam("roomAcc") String roomAcc,
			@PathParam("userMineAcc") String userMineAcc, String message) {

		message = userName + ":" + message;
		WebSocketUtil.sendMessageToAll(message, roomAcc, userMineAcc);
	}

	// 离开聊天室
	@OnClose
	public void onClose(@PathParam("userName") String userName, @PathParam("roomAcc") String roomAcc,
			@PathParam("userMineAcc") String userMineAcc, Session session) {
		// 将当前用户从map中移除 注销
		

		// 群发消息
		// WebSocketUtil.sendMessageToAll("用户:" + userName + "离开聊天室");
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
}
