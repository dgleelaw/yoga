package com.woniuxy.yogasystem.service.imp;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.woniuxy.yogasystem.dao.AddressDao;
import com.woniuxy.yogasystem.pojo.Address;
import com.woniuxy.yogasystem.service.AddressService;
@Transactional
@Service("AddressService")
public class AddressServiceImp implements AddressService {
	@Resource
	private AddressDao addressDao;

	@Override
	public Address findAddressById(int otherUid) {
		Address address = new Address();
		address=addressDao.findAddressById(otherUid);
		return address;
	}

}
