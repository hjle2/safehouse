package com.ssafy.happyhouse.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dto.HouseDeal;
import com.ssafy.happyhouse.dto.HouseDealDetail;
import com.ssafy.happyhouse.dto.SidoGugunCodeDto;
import com.ssafy.happyhouse.service.HouseDealService;
import com.ssafy.happyhouse.service.HouseMapService;

@RestController
@RequestMapping("/house")
public class RestHouseController {
	private HouseDealService hService;
	private HouseMapService hMapService;
	private final Logger logger = LoggerFactory.getLogger(RestHouseController.class);
	@Autowired
	public RestHouseController(HouseDealService hService, HouseMapService hMapService) {
		super();
		this.hService = hService;
		this.hMapService = hMapService;
	}
	
	@GetMapping("/deallist")
	public ResponseEntity<?> houseDeals(String juso, Model model) {
		StringTokenizer st= new StringTokenizer(juso);
		st.nextToken();
		st.nextToken();
		String DongCode = st.nextToken(); 
		List<HouseDeal> houselist;
		try {
			houselist = hService.selectHouseDeals(DongCode);
			return new ResponseEntity<List<HouseDeal>>(houselist, HttpStatus.OK);
		} catch (SQLException e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	@GetMapping("/dealist/detail")
	public ResponseEntity<List<HouseDealDetail>> houseDetail (@RequestParam("aptCode") String aptCode){
//		
		try {
			List<HouseDealDetail> list = hService.selectDealDetails(aptCode);
			return new ResponseEntity<List<HouseDealDetail>>(list,HttpStatus.OK);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping("/sido")
	public ResponseEntity<List<SidoGugunCodeDto>> Sido(){
		logger.info("sido - 호출");
		List<SidoGugunCodeDto> addresslist;
		
		try {
			addresslist = hMapService.selectsidolist();
			return new ResponseEntity<List<SidoGugunCodeDto>>(addresslist,HttpStatus.OK);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<List<SidoGugunCodeDto>>(HttpStatus.NO_CONTENT);
		}
	}
	@GetMapping("/gugun")
	public ResponseEntity<List<SidoGugunCodeDto>> gugun(@RequestParam("sido") String sido)  {
		logger.info("gugun - 호출");
		try {
			return new ResponseEntity<List<SidoGugunCodeDto>>(hMapService.selectgugunlist(sido), HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<List<SidoGugunCodeDto>>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/dong")
	public ResponseEntity<List<SidoGugunCodeDto>> dong(@RequestParam("gugun") String gugun) {
		logger.info("dong - 호출");
		try {
			return new ResponseEntity<List<SidoGugunCodeDto>>(hMapService.selectdonglist(gugun), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<SidoGugunCodeDto>>(HttpStatus.NO_CONTENT);
		}
	}


}
