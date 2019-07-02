package com.woniuxy.yogasystem.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.woniuxy.yogasystem.pojo.Coach;
import com.woniuxy.yogasystem.pojo.Order_Form;
import com.woniuxy.yogasystem.pojo.Request_Message;
import com.woniuxy.yogasystem.pojo.Trainee;
import com.woniuxy.yogasystem.pojo.Venues;

public interface CoachService {
	
	public Coach findCoach(int id);
	
	public String signVenues(int cid,int vid);
	
	public List<Request_Message> findResMessage(int id);
	
	public List<Request_Message> findHintMessage(int id);
	
	public String adopt(Order_Form order,int mid, int uid1, int uid2,int pid);
	
	public String refuse(int cid,int tid,int mid);
	
	public List<Venues> findVenues(Venues venues);
	
	public List<Trainee> findTrainee(int uid);
	
	public List<Venues> findMyVenues(int uid);
	
	public String removeMes(int id);
	
	public String adoptVenues(int uid,int cid,int mid);
}
