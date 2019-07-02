package com.woniuxy.yogasystem.pojo;

import java.util.List;

public class User {
	private int id;
	private String acc;
	
	private String pwd;
	private int role;
	private List<String> authoritys;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAcc() {
		return acc;
	}
	public void setAcc(String acc) {
		this.acc = acc;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public List<String> getAuthoritys() {
		return authoritys;
	}
	public void setAuthoritys(List<String> authoritys) {
		this.authoritys = authoritys;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(int id, String acc, String pwd, int role) {
		super();
		this.id = id;
		this.acc = acc;
		this.pwd = pwd;
		this.role = role;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", acc=" + acc + ", pwd=" + pwd + ", role=" + role + ", authoritys=" + authoritys
				+ "]";
	}
	
}
