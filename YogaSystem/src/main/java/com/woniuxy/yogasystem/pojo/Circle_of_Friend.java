package com.woniuxy.yogasystem.pojo;

import java.util.List;

public class Circle_of_Friend {
	private int id;
	private int uid;
	private String name;
	private String head;
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	private String title;
	private String content;
	private String time;
	private String img;
	private int flag;
	private List<View> list;
	
	public List<View> getList() {
		return list;
	}
	public void setList(List<View> list) {
		this.list = list;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	@Override
	public String toString() {
		return "Circle_of_Friend [id=" + id + ", uid=" + uid + ", name=" + name + ", head=" + head + ", title=" + title
				+ ", content=" + content + ", time=" + time + ", img=" + img + ", flag=" + flag + ", list=" + list
				+ "]";
	}
	
	
	
	
}
