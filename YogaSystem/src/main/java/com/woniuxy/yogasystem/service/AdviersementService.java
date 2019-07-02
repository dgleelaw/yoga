package com.woniuxy.yogasystem.service;

import java.util.List;



import com.woniuxy.yogasystem.dao.AdviersementDao;
import com.woniuxy.yogasystem.pojo.Adviersement;

public interface AdviersementService {
	// 插入广告
	public void insertAd(Adviersement ad);

	// 删除广告
	public void deleteAd(int id);

	// 查询广告
	public List<Adviersement> findAllAd();
}
