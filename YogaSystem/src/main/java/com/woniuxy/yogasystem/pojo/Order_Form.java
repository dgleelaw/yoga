package com.woniuxy.yogasystem.pojo;

public class Order_Form {
	private int id;
	private int tid;
	private int cid;
	private int vid;
	private int price;
	private String startTime;
	private int status;
	private String number;
	private Coach coach;
	private Venues venues;
	private Trainee trainee;
	private String tname;
	private String vname;
	private String cname;
	private String order_eva_id;
	private Integer level;
	private String detail;
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
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public Coach getCoach() {
		return coach;
	}
	public void setCoach(Coach coach) {
		this.coach = coach;
	}
	public Venues getVenues() {
		return venues;
	}
	public void setVenues(Venues venues) {
		this.venues = venues;
	}
	public Trainee getTrainee() {
		return trainee;
	}
	public void setTrainee(Trainee trainee) {
		this.trainee = trainee;
	}
	@Override
	public String toString() {
		return "Order_Form [id=" + id + ", tid=" + tid + ", cid=" + cid + ", vid=" + vid + ", price=" + price
				+ ", startTime=" + startTime + ", status=" + status + ", number=" + number + ", coach=" + coach
				+ ", venues=" + venues + ", trainee=" + trainee + "]";
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public String getVname() {
		return vname;
	}
	public void setVname(String vname) {
		this.vname = vname;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	
	public String getOrder_eva_id() {
		return order_eva_id;
	}
	public void setOrder_eva_id(String order_eva_id) {
		this.order_eva_id = order_eva_id;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	
	
	
}
