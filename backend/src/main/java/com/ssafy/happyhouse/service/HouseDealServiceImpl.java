package com.ssafy.happyhouse.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dto.HouseDeal;
import com.ssafy.happyhouse.dto.HouseDealDetail;
import com.ssafy.happyhouse.dto.SidoGugunCodeDto;
import com.ssafy.happyhouse.mapper.HouseDealMapper;

@Service
public class HouseDealServiceImpl implements HouseDealService {

	HouseDealMapper mapper;
	
	public HouseDealServiceImpl(HouseDealMapper mapper) {
		super();
		this.mapper = mapper;
	}

	@Override
	public List<HouseDeal> selectHouseDeals(String dongCode) throws SQLException {
		return mapper.selectHouseDeals(dongCode);
	}

	@Override
	public List<HouseDealDetail> selectDealDetails(String aptCode) throws SQLException {
		return mapper.selectDealDetails(aptCode);
	}

	


}
