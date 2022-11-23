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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dto.CrimePoliceDto;
import com.ssafy.happyhouse.dto.HouseDeal;
import com.ssafy.happyhouse.dto.HouseDealDetail;
import com.ssafy.happyhouse.dto.SidoGugunCodeDto;
import com.ssafy.happyhouse.dto.policeoffice;
import com.ssafy.happyhouse.service.HouseDealService;
import com.ssafy.happyhouse.service.HouseMapService;

@RestController
@RequestMapping("/house")
@CrossOrigin("*")
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
	@GetMapping("/list/police")
	public ResponseEntity<?> policeinfo(){
		System.out.println("police 도착");
		try {
			List<CrimePoliceDto> location= hService.selectofficelocation();
			return new ResponseEntity<List<CrimePoliceDto>>(location,HttpStatus.OK);
		} catch (SQLException e) {
			e.printStackTrace();
			return new ResponseEntity<policeoffice>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@GetMapping("/list/dong")
	public ResponseEntity<?> houseDeals(@RequestParam(value="code" ,required=false)String code) {
		System.out.println("dong 도착");
		List<HouseDeal> houselist;
		try {
			
			houselist = hService.selectHouseDeals(code);
			return new ResponseEntity<List<HouseDeal>>(houselist, HttpStatus.OK);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("데이터 에러 발생");
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/list/sido")
	public ResponseEntity<?> housesidoDeals(@RequestParam(value="code" ,required=false)String code) {
		System.out.println("시도 도착");
		List<HouseDeal> houselist;
		try {
			houselist = hService.selectsidoHouseDeals(code);
			return new ResponseEntity<List<HouseDeal>>(houselist, HttpStatus.OK);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("데이터 에러 발생");
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/list/gugun")
	public ResponseEntity<?> housegugunDeals(@RequestParam(value="code" ,required=false)String code) {
		System.out.println("구군 도착");
		List<HouseDeal> houselist;
		try {
			houselist = hService.selectgugunHouseDeals(code);
			return new ResponseEntity<List<HouseDeal>>(houselist, HttpStatus.OK);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("데이터 에러 발생");
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
