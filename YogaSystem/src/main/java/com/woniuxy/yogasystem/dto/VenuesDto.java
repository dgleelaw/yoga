package com.woniuxy.yogasystem.dto;

import java.util.List;

import com.woniuxy.yogasystem.pojo.Venues_Img;

public class VenuesDto {
	private String province;
	private String city;
	private String county;
	private String town;
	private String descrie;
	private String detail;
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getDescrie() {
		return descrie;
	}
	public void setDescrie(String descrie) {
		this.descrie = descrie;
	}
	private String phone;
	private String name;
	private String img;
	private int id;
	private int uid;
	private float vx;
	private float vy;
	private List<Venues_Img>list;
	
	public List<Venues_Img> getList() {
		return list;
	}
	public void setList(List<Venues_Img> list) {
		this.list = list;
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

	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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
		return "VenuesDto [province=" + province + ", city=" + city + ", county=" + county + ", town=" + town
				+ ", descrie=" + descrie + ", phone=" + phone + ", name=" + name + ", img=" + img + ", id=" + id
				+ ", uid=" + uid + ", vx=" + vx + ", vy=" + vy + ", list=" + list + "]";
	}
	
	
}
