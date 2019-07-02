package com.woniuxy.yogasystem.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.woniuxy.yogasystem.dao.AdviersementDao;
import com.woniuxy.yogasystem.pojo.Adviersement;
import com.woniuxy.yogasystem.service.AdviersementService;

@Controller
@RequestMapping("/ad") // 广告
public class AdviersementController {
	@Autowired
	private AdviersementService adviersementService;

	public AdviersementService getAdviersementService() {
		return adviersementService;
	}

	public void setAdviersementService(AdviersementService adviersementService) {
		this.adviersementService = adviersementService;
	}

	// 插入广告
	@RequestMapping("/insertAd")
	@ResponseBody
	public String insertAd(Adviersement ad) {
		try {
			adviersementService.insertAd(ad);
			return "广告新增成功";
		} catch (Exception e) {
			// TODO: handle exception
			return "广告新增失败,系统崩溃请联系程序员";
		}
	}

	// 删除广告
	@RequestMapping("/deleteAd")
	@ResponseBody
	public String deleteAd(int[] id) {
		try {
			for (int i = 0; i < id.length; i++) {
				adviersementService.deleteAd(id[i]);
			}
			
			return "广告删除成功";
		} catch (Exception e) {
			// TODO: handle exception
			return "广告删除失败,系统崩溃请联系程序员";
		}
		
	}

	// 查询广告
	@RequestMapping("/findAllAd")
	@ResponseBody
	public List<Adviersement> findThreeAd() {
		List<Adviersement>list= adviersementService.findAllAd();
		return list;
		
	}
}
