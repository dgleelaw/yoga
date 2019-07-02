package com.woniuxy.yogasystem.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.woniuxy.yogasystem.service.imp.DtoDaoServiceImpl;

@Controller
@RequestMapping("/dto")
public class DtoController {
	@Autowired
	private DtoDaoServiceImpl dtoDaoServiceImpl;
@RequestMapping("/findAll")
@ResponseBody
public Map<String, Object> findAll(){
	
	dtoDaoServiceImpl.findAllVenuesDto();
	Map<String, Object> map=new HashMap<String, Object>();
	map.put("coachs", dtoDaoServiceImpl.findAllCoachDto());
	map.put("venues", dtoDaoServiceImpl.findAllVenuesDto());
	return map;
	
}
}
