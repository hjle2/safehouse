package com.ssafy.happyhouse.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.dto.CrimePoliceDto;
import com.ssafy.happyhouse.dto.HouseDeal;
import com.ssafy.happyhouse.dto.HouseDealDetail;
import com.ssafy.happyhouse.dto.SidoGugunCodeDto;
import com.ssafy.happyhouse.dto.policeoffice;

public interface HouseDealService {
	List<HouseDeal> selectHouseDeals(String dongCode) throws SQLException;
	List<HouseDeal> selectsidoHouseDeals(String dongCode) throws SQLException;
	List<HouseDeal> selectgugunHouseDeals(String dongCode) throws SQLException;
	List<HouseDealDetail> selectDealDetails(String aptCode) throws SQLException;
	List<CrimePoliceDto> selectofficelocation() throws SQLException;

}

