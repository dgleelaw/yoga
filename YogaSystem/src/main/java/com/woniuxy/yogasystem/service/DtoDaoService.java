package com.woniuxy.yogasystem.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.woniuxy.yogasystem.dto.CoachDto;
import com.woniuxy.yogasystem.dto.VenuesDto;

public interface DtoDaoService {
	public List<CoachDto> findAllCoachDto();
	public List<VenuesDto> findAllVenuesDto();
}
