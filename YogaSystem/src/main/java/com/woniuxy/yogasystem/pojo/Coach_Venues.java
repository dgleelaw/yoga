package com.woniuxy.yogasystem.pojo;

public class Coach_Venues {
	private int id;
	private int cid;
	private int vid;
	private int salary;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public int getVid() {
		return vid;
	}
	public void setVid(int vid) {
		this.vid = vid;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public Coach_Venues() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Coach_Venues(int cid, int vid, int salary) {
		super();
		this.cid = cid;
		this.vid = vid;
		this.salary = salary;
	}
	
}
