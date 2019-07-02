package com.woniuxy.yogasystem.pojo;

public class Address {
private int uid;
private String province;
private String city;
private String county;
private String town;
private String detail;
public int getUid() {
	return uid;
}
public void setUid(int uid) {
	this.uid = uid;
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
public Address(int uid, String province, String city, String county, String town, String detail) {
	super();
	this.uid = uid;
	this.province = province;
	this.city = city;
	this.county = county;
	this.town = town;
	this.detail = detail;
}
public Address() {
	super();
	// TODO Auto-generated constructor stub
}
@Override
public String toString() {
	return "Address [uid=" + uid + ", province=" + province + ", city=" + city + ", county=" + county + ", town=" + town
			+ ", detail=" + detail + "]";
}


}
