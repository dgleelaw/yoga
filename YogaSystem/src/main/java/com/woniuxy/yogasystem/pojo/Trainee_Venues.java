package com.woniuxy.yogasystem.pojo;

public class Trainee_Venues {
	private int id;
	private int tid;
	private int vid;
	private int flag;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public int getVid() {
		return vid;
	}
	public void setVid(int vid) {
		this.vid = vid;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	@Override
	public String toString() {
		return "Trainee_Venues [id=" + id + ", tid=" + tid + ", vid=" + vid + ", flag=" + flag + "]";
	}
	
}
