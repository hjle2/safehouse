package com.ssafy.happyhouse.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dto.SidoGugunCodeDto;
import com.ssafy.happyhouse.mapper.HouseDealMapper;

@Service
public class HouseMapServiceImpl implements HouseMapService{

	@Autowired
	HouseDealMapper mapper;
	
	@Override
	public List<SidoGugunCodeDto> selectsidolist() throws SQLException {
		return mapper.getSido();
	}
	
	@Override
	public List<SidoGugunCodeDto> selectgugunlist(String sido) throws Exception {
		// TODO Auto-generated method stub
		return mapper.getGugunInSido(sido);
	}
	
	@Override
	public List<SidoGugunCodeDto> selectdonglist(String gugun) throws Exception {
		return mapper.getDongInGugun(gugun);
	}
}
