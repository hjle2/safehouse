package com.ssafy.happyhouse.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dto.PoliceStationDto;
import com.ssafy.happyhouse.dto.SidoGugunCodeDto;
import com.ssafy.happyhouse.dto.policeoffice;
import com.ssafy.happyhouse.mapper.DataMapper;

@RestController
@RequestMapping("/api")
public class DataController {
	
	@Autowired
	DataMapper mapper;
	// @Autowired
	// DatainsertSerive
	@GetMapping("/dong")
	public void InsertDong() throws IOException {
		BufferedReader br  = new BufferedReader(new FileReader("C:\\Users\\multicampus\\Desktop\\data\\data2.csv"));
		//List<SidoGugunCodeDto> donglist; 
		while(true) {
			String s = br.readLine();
			if(s == null) break;
			StringTokenizer st = new StringTokenizer(s,",");
			String sidoName = st.nextToken();
			String gugunName = st.nextToken();
			String dongName = st.nextToken();
			String dongCode = st.nextToken();
			
			if(sidoName.equals(gugunName)) continue;
			SidoGugunCodeDto temp = new SidoGugunCodeDto();
			temp.setSidoName(sidoName);
			temp.setGugunName(gugunName);
			temp.setDongName(dongName);
			temp.setDongCode(dongCode);
			
			System.out.println(sidoName + " "+ gugunName + " " + dongName + " " + dongCode);
			mapper.insertDong(temp);
		}
	}
	
	
	@GetMapping("/police")
	public void InsertData() throws IOException {
		BufferedReader br  = new BufferedReader(new FileReader("C:\\Users\\multicampus\\Desktop\\bbb\\dataset\\2020GradeDataFix2.csv"));
		List<PoliceStationDto> data = new ArrayList<>();
		int i =0;
		while(true) {
			String s = br.readLine();
			if(s == null) break;
			if(i == 0) {
				i++;
				continue;
			}
			StringTokenizer st = new StringTokenizer(s,",");
			st.nextToken();
			int year = Integer.parseInt(st.nextToken());
			String Policestation = st.nextToken();
			int murder = Integer.parseInt(st.nextToken());
			int rob = Integer.parseInt(st.nextToken());
			int theft = Integer.parseInt(st.nextToken());
			int assualt = Integer.parseInt(st.nextToken());
			int murderGrade = Integer.parseInt(st.nextToken());
			int robGrade = Integer.parseInt(st.nextToken());
			int theftGrade = Integer.parseInt(st.nextToken());
			int assualtGrade = Integer.parseInt(st.nextToken());
			String aver = st.nextToken();
			data.add(new PoliceStationDto(year,Policestation,murder,rob,theft,assualt,murderGrade,robGrade,theftGrade,assualtGrade,aver));
		}
		
		for( PoliceStationDto row :data) {
			mapper.insertcrime(row);
		}
		
		
	}
	
	@GetMapping("/location")
	public void InsertLocation() throws IOException {
		BufferedReader br  = new BufferedReader(new FileReader("C:\\Users\\multicampus\\Desktop\\bbb\\dataset\\policefixed2.csv"));
		List<policeoffice> policelist = new ArrayList<>();
		int i = 0;
		while(true) {
			String s = br.readLine();
			if(s == null) break;
			if(i == 0) {
				i+=1;
				continue;
			}
			//System.out.println(s);
			StringTokenizer st = new StringTokenizer(s,",");
			st.nextToken();
			String name = st.nextToken();
			String addr = st.nextToken();
			policelist.add(new policeoffice(name,addr));
			
		}
		System.out.println(policelist.get(0));
		
		for(policeoffice v :policelist) {
			mapper.insertpolice(v);
		}
		
		
	}
	
	
}
