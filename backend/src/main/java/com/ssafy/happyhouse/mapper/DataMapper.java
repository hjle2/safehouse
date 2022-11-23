package com.ssafy.happyhouse.mapper;

import com.ssafy.happyhouse.dto.PoliceStationDto;
import com.ssafy.happyhouse.dto.SidoGugunCodeDto;
import com.ssafy.happyhouse.dto.policeoffice;

public interface DataMapper {

	void insertDong(SidoGugunCodeDto dong);
	void insertcrime(PoliceStationDto dto);
	void insertpolice(policeoffice data);
}
