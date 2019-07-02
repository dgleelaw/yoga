package com.woniuxy.yogasystem.pojo;

public class Apply {
private int uid;
private String img;
private String name;
private String phone;
private	String descrie;
private int flag;
private int salary;
private  int infostatus;
private  String school;
private String authstatus;
private int authentication;
private int privatetime;
private int  sex;
private float vx;
private float vy;
public int getUid() {
	return uid;
}
public void setUid(int uid) {
	this.uid = uid;
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
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
public String getDescrie() {
	return descrie;
}
public void setDescrie(String descrie) {
	this.descrie = descrie;
}
public int getFlag() {
	return flag;
}
public void setFlag(int flag) {
	this.flag = flag;
}
public int getSalary() {
	return salary;
}
public void setSalary(int salary) {
	this.salary = salary;
}
public int getInfostatus() {
	return infostatus;
}
public void setInfostatus(int infostatus) {
	this.infostatus = infostatus;
}
public String getSchool() {
	return school;
}
public void setSchool(String school) {
	this.school = school;
}
public String getAuthstatus() {
	return authstatus;
}
public void setAuthstatus(String authstatus) {
	this.authstatus = authstatus;
}
public int getAuthentication() {
	return authentication;
}
public void setAuthentication(int authentication) {
	this.authentication = authentication;
}
public int getPrivatetime() {
	return privatetime;
}
public void setPrivatetime(int privatetime) {
	this.privatetime = privatetime;
}
public int getSex() {
	return sex;
}
public void setSex(int sex) {
	this.sex = sex;
}
public float getVx() {
	return vx;
}
public void setVx(float vx) {
	this.vx = vx;
}
public float getVy() {
	return vy;
}
public void setVy(float vy) {
	this.vy = vy;
}
public Apply(int uid, String img, String name, String phone, String descrie, int flag, int salary, int infostatus,
		String school, String authstatus, int authentication, int privatetime, int sex, float vx, float vy) {
	super();
	this.uid = uid;
	this.img = img;
	this.name = name;
	this.phone = phone;
	this.descrie = descrie;
	this.flag = flag;
	this.salary = salary;
	this.infostatus = infostatus;
	this.school = school;
	this.authstatus = authstatus;
	this.authentication = authentication;
	this.privatetime = privatetime;
	this.sex = sex;
	this.vx = vx;
	this.vy = vy;
}
public Apply() {
	super();
	// TODO Auto-generated constructor stub
}
@Override
public String toString() {
	return "Apply [uid=" + uid + ", img=" + img + ", name=" + name + ", phone=" + phone + ", descrie=" + descrie
			+ ", flag=" + flag + ", salary=" + salary + ", infostatus=" + infostatus + ", school=" + school
			+ ", authstatus=" + authstatus + ", authentication=" + authentication + ", privatetime=" + privatetime
			+ ", sex=" + sex + ", vx=" + vx + ", vy=" + vy + "]";
}

}
