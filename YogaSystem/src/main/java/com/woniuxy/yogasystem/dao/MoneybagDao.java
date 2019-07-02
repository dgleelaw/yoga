package com.woniuxy.yogasystem.dao;

import org.apache.ibatis.annotations.Insert;

public interface MoneybagDao {
	
	@Insert("insert into moneybag(uid) values(#{uid})")
	public boolean addMon(int uid);
}
