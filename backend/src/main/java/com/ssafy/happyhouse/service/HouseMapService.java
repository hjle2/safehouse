package com.ssafy.happyhouse.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.dto.SidoGugunCodeDto;

public interface HouseMapService {
	List<SidoGugunCodeDto> selectsidolist() throws SQLException;
	List<SidoGugunCodeDto> selectgugunlist(String sido) throws Exception;
	List<SidoGugunCodeDto> selectdonglist(String gugun) throws Exception;
}
