package com.woniuxy.yogasystem.pojo;

public class View {
public int id;
public int cid;
public String text;
public String img;
public String time;
public String name;
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getTime() {
	return time;
}
public void setTime(String time) {
	this.time = time;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}


@Override
public String toString() {
	return "View [id=" + id + ", cid=" + cid + ", text=" + text + ", img=" + img + ", time=" + time + ", name=" + name
			+ "]";
}
public int getCid() {
	return cid;
}
public void setCid(int cid) {
	this.cid = cid;
}
public String getText() {
	return text;
}
public void setText(String text) {
	this.text = text;
}
public String getImg() {
	return img;
}
public void setImg(String img) {
	this.img = img;
}
}