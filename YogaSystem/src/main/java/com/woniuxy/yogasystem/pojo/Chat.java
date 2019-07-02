package com.woniuxy.yogasystem.pojo;

public class Chat {
	private int id;
	private int mid;
	private int yid;
	private int state;
	private String chatmessage;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public int getYid() {
		return yid;
	}
	public void setYid(int yid) {
		this.yid = yid;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getChatmessage() {
		return chatmessage;
	}
	public void setChatmessage(String chatmessage) {
		this.chatmessage = chatmessage;
	}
	public Chat(int id, int mid, int yid, int state, String chatmessage) {
		super();
		this.id = id;
		this.mid = mid;
		this.yid = yid;
		this.state = state;
		this.chatmessage = chatmessage;
	}
	public Chat() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
