package com.woniuxy.yogasystem.pojo;

public class Adviersement {
	private int id;
	private String title;
	private String content;
	private String img;
	private int flag;
	@Override
	public String toString() {
		return "Adviersement [id=" + id + ", title=" + title + ", content=" + content + ", img=" + img + ", flag="
				+ flag + "]";
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	
}
