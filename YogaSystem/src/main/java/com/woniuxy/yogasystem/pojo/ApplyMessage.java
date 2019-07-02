package com.woniuxy.yogasystem.pojo;

public class ApplyMessage {
private int uid;
private String name;
private String phone;
private String img;
private int salary;
private String school;
private int sex;
private String authstatus ;
private int type;
private int flag;
private String descrie;
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
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
public String getImg() {
	return img;
}
public void setImg(String img) {
	this.img = img;
}
public int getSalary() {
	return salary;
}
public void setSalary(int salary) {
	this.salary = salary;
}
public String getSchool() {
	return school;
}
public void setSchool(String school) {
	this.school = school;
}
public int getSex() {
	return sex;
}
public void setSex(int sex) {
	this.sex = sex;
}
public String getAuthstatus() {
	return authstatus;
}
public void setAuthstatus(String authstatus) {
	this.authstatus = authstatus;
}
public int getType() {
	return type;
}
public void setType(int type) {
	this.type = type;
}
public int getFlag() {
	return flag;
}
public void setFlag(int flag) {
	this.flag = flag;
}

public String getDescrie() {
	return descrie;
}
public void setDescrie(String descrie) {
	this.descrie = descrie;
}
public ApplyMessage(int uid, String name, String phone, String img, int salary, String school, int sex,
		String authstatus, int type, int flag, String descrie) {
	super();
	this.uid = uid;
	this.name = name;
	this.phone = phone;
	this.img = img;
	this.salary = salary;
	this.school = school;
	this.sex = sex;
	this.authstatus = authstatus;
	this.type = type;
	this.flag = flag;
	this.descrie = descrie;
}
public ApplyMessage() {
	super();
	// TODO Auto-generated constructor stub
}
@Override
public String toString() {
	return "ApplyMessage [uid=" + uid + ", name=" + name + ", phone=" + phone + ", img=" + img + ", salary=" + salary
			+ ", school=" + school + ", sex=" + sex + ", authstatus=" + authstatus + ", type=" + type + ", flag=" + flag
			+ ", descrie=" + descrie + "]";
}


}
