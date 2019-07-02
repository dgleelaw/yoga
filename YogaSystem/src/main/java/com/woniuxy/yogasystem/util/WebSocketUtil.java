package com.woniuxy.yogasystem.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;
import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;



import com.woniuxy.yogasystem.service.ChatService;


public class WebSocketUtil {
	@Resource
	private static ChatService chatService;
	// public static
	public static int i = 0;
	public static List<String> roomAccount = new ArrayList<>();
	// HashMap：不支持多线程，并发情况下线程不安全
	
	public static final Map<String, Session> MESSAGEMAPGROUP1 = new ConcurrentHashMap<>();
	public static final Map<String, Session> MESSAGEMAPGROUP2 = new ConcurrentHashMap<>();
	public static final Map<String, Session> MESSAGEMAPGROUP3 = new ConcurrentHashMap<>();
	public static final Map<String, Session> MESSAGEMAPGROUP4 = new ConcurrentHashMap<>();
	public static final Map<String, Session> MESSAGEMAPGROUP5 = new ConcurrentHashMap<>();
	public static final Map<String, Session> MESSAGEMAPGROUP6 = new ConcurrentHashMap<>();
	// 一对一聊天Map
	public static final Map<String, Session> ONETOONEMAP = new ConcurrentHashMap<>();

	// 发送消息给客户端
	public static void sendMessage(Session session, String message, String otherAcc, String mineAcc) {

		if (session != null) {
			final RemoteEndpoint.Basic basic = session.getBasicRemote();
			if (basic != null) {

				try {
					basic.sendText(message);// 发送消息回客户端

				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}
	}

	// 将消息给所有聊天室的人


	// 循环发送
	public static void sendMessageToAll(String message, String roomAcc, String mineAcc) {
		switch (roomAcc) {
		case "6":
			MESSAGEMAPGROUP1.forEach((sessionId,session)-> sendMessage(session, message, roomAcc, mineAcc));
			break;
		case "22":
			MESSAGEMAPGROUP2.forEach((sessionId,session)-> sendMessage(session, message, roomAcc, mineAcc));
			break;
		default:
			break;
		}

	}

	// 将消息发送指定人
	public static void sendMessageToOne(String message, String otherAcc, String mineAcc) {
		Session otherSession = ONETOONEMAP.get(otherAcc);
		Session mineSession = ONETOONEMAP.get(mineAcc);

		sendMessage(otherSession, message, otherAcc, mineAcc);
		sendMessage(mineSession, message, otherAcc, mineAcc);
	}
	
	public static boolean checkMessage(String otherAcc){	
		//不存在
		if(ONETOONEMAP.get(otherAcc)==null){
			System.out.println(ONETOONEMAP.get(otherAcc));
			return true;
		}
		return false;
	}

}
