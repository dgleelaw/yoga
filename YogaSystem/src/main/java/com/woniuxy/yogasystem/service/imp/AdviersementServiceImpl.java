package com.woniuxy.yogasystem.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.woniuxy.yogasystem.dao.AdviersementDao;
import com.woniuxy.yogasystem.pojo.Adviersement;
import com.woniuxy.yogasystem.service.AdviersementService;

@Transactional
@Service("AdviersementService")
public class AdviersementServiceImpl implements AdviersementService {
	@Autowired
	private AdviersementDao adviersementDao;

	@Override // 插入广告
	public void insertAd(Adviersement ad) {
		adviersementDao.insertAd(ad);
	}

	@Override // 删除广告
	public void deleteAd(int id) {
		// TODO Auto-generated method stub
		adviersementDao.deleteAd(id);
	}

	@Override // 查询广告
	public List<Adviersement> findAllAd() {
		// TODO Auto-generated method stub
		return adviersementDao.findAllAd();
	}

}
