package com.ssafy.happyhouse.mapper;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.dto.CrimePoliceDto;
import com.ssafy.happyhouse.dto.HouseDeal;
import com.ssafy.happyhouse.dto.HouseDealDetail;
import com.ssafy.happyhouse.dto.SidoGugunCodeDto;
import com.ssafy.happyhouse.dto.policeoffice;

public interface HouseDealMapper {
	List<HouseDeal> selectHouseDeals(String dongCode) throws SQLException;
	List<HouseDeal> selectsidoHouseDeals(String sido) throws SQLException;
	List<HouseDeal> selectgugunHouseDeals(String gugun) throws SQLException;
	List<CrimePoliceDto> selectpoliceinfo() throws SQLException;
	List<HouseDealDetail> selectDealDetails(String aptCode) throws SQLException;
	List<SidoGugunCodeDto> getSido() throws SQLException;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception;
	List<SidoGugunCodeDto> getDongInGugun(String gugun) throws Exception;
}
