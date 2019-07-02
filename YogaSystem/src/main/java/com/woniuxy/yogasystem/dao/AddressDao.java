package com.woniuxy.yogasystem.dao;

import org.apache.ibatis.annotations.Select;

import com.woniuxy.yogasystem.pojo.Address;

public interface AddressDao {
	
	@Select("select * from address where uid=#{uid}")
	public Address findAddress(int uid);
	
	@Select("SELECT * FROM address WHERE uid=#{otherUid}")
	Address findAddressById(int otherUid);
}
