package com.woniuxy.yogasystem.pojo;

import java.util.List;

public class Follows {
	private int id;
	private int uid;
	private int followuid;
	@Override
	public String toString() {
		return "Follows [id=" + id + ", uid=" + uid + ", followuid=" + followuid + ", img=" + img + ", name=" + name
				+ ", myimg=" + myimg + ", myname=" + myname + ", character=" + character + ", status=" + status + "]";
	}
	private String img;
	private String name;
	private String myimg;
	private String myname;
	public String getMyimg() {
		return myimg;
	}
	public void setMyimg(String myimg) {
		this.myimg = myimg;
	}
	public String getMyname() {
		return myname;
	}
	public void setMyname(String myname) {
		this.myname = myname;
	}
	private int character;
	private int status;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCharacter() {
		return character;
	}
	public void setCharacter(int character) {
		this.character = character;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getFollowuid() {
		return followuid;
	}
	public void setFollowuid(int followuid) {
		this.followuid = followuid;
	}
	
}
