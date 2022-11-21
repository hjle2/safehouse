package com.ssafy.happyhouse.mapper;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.dto.HouseDeal;
import com.ssafy.happyhouse.dto.HouseDealDetail;
import com.ssafy.happyhouse.dto.SidoGugunCodeDto;

public interface HouseDealMapper {
	List<HouseDeal> selectHouseDeals(String dongCode) throws SQLException;
	List<HouseDealDetail> selectDealDetails(String aptCode) throws SQLException;
	List<SidoGugunCodeDto> getSido() throws SQLException;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception;
	List<SidoGugunCodeDto> getDongInGugun(String gugun) throws Exception;
}