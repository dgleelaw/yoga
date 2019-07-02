package com.woniuxy.yogasystem.pojo;

public class Request_Message {
	private int id;
	private int uid1;
	private int uid2;
	private int price;
	private int vid;
	private int pid;
	private String content;
	private String name;
	private String img;
	private int character;
	private int type;
	private Private_Course private_Course;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	
	public int getCharacter() {
		return character;
	}
	public void setCharacter(int character) {
		this.character = character;
	}
	public Request_Message() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getUid1() {
		return uid1;
	}
	public void setUid1(int uid1) {
		this.uid1 = uid1;
	}
	public int getUid2() {
		return uid2;
	}
	public void setUid2(int uid2) {
		this.uid2 = uid2;
	}
	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getVid() {
		return vid;
	}
	public void setVid(int vid) {
		this.vid = vid;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Request_Message(int uid1, int uid2, String content, String name, String img, int character, int type) {
		super();
		this.uid1 = uid1;
		this.uid2 = uid2;
		this.content = content;
		this.name = name;
		this.img = img;
		this.character = character;
		this.type = type;
	}
	public Request_Message(int uid1, int uid2, int price, int vid, int pid, String content, String name, String img,
			int character, int type) {
		super();
		this.uid1 = uid1;
		this.uid2 = uid2;
		this.price = price;
		this.vid = vid;
		this.pid = pid;
		this.content = content;
		this.name = name;
		this.img = img;
		this.character = character;
		this.type = type;
	}
	public Private_Course getPrivate_Course() {
		return private_Course;
	}
	public void setPrivate_Course(Private_Course private_Course) {
		this.private_Course = private_Course;
	}
	
	
	
	
	
	
	
}
