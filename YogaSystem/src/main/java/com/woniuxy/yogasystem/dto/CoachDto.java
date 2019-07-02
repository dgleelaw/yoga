package com.woniuxy.yogasystem.dto;

public class CoachDto {
private String province;
private String city;
private String county;
private String town;
private String detail;
private String phone;
private String school;
private String name;
private String img;
private String authstatus;
private String authentication;
private int id;
private int uid;
private float vx;
private float vy;
public String getAuthstatus() {
	return authstatus;
}
public void setAuthstatus(String authstatus) {
	this.authstatus = authstatus;
}
public String getAuthentication() {
	return authentication;
}
public void setAuthentication(String authentication) {
	this.authentication = authentication;
}

public String getProvince() {
	return province;
}
public void setProvince(String province) {
	this.province = province;
}
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
public String getCounty() {
	return county;
}
public void setCounty(String county) {
	this.county = county;
}
public String getTown() {
	return town;
}
public void setTown(String town) {
	this.town = town;
}
public String getDetail() {
	return detail;
}
public void setDetail(String detail) {
	this.detail = detail;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
public String getSchool() {
	return school;
}
public void setSchool(String school) {
	this.school = school;
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
@Override
public String toString() {
	return "CoachDto [province=" + province + ", city=" + city + ", county=" + county + ", town=" + town + ", detail="
			+ detail + ", phone=" + phone + ", school=" + school + ", name=" + name + ", img=" + img + ", authstatus="
			+ authstatus + ", authentication=" + authentication + ", id=" + id + ", uid=" + uid + ", vx=" + vx + ", vy="
			+ vy + "]";
}


}
