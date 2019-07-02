package com.woniuxy.yogasystem.pojo;

import java.util.List;

public class UnReadChatMess {
	private int mid;
	private int yid;
	private String img;
	List<String> message;

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
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

	public List<String> getMessage() {
		return message;
	}

	public void setMessage(List<String> message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "UnReadChatMess [mid=" + mid + ", yid=" + yid + ", message=" + message + "]";
	}

}
