package com.woniuxy.yogasystem.pojo;

public class Venues {
	private int id;
	private String name;
	private String addr;
	private String phone;
	private int salary;
	private String descrie;
	private int uid;
	private String img;
	private float vx;
	private float vy;
	private Address address;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
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
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public String getDescrie() {
		return descrie;
	}
	public void setDescrie(String descrie) {
		this.descrie = descrie;
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
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "Venues [id=" + id + ", name=" + name + ", addr=" + addr + ", phone=" + phone + ", salary=" + salary
				+ ", descrie=" + descrie + ", uid=" + uid + ", img=" + img + ", vx=" + vx + ", vy=" + vy + ", address="
				+ address + "]";
	}
	
	
}
