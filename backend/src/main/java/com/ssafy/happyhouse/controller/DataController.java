package com.ssafy.happyhouse.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dto.PoliceStationDto;

@RestController
@RequestMapping("/api")
public class DataController {
	
	// @Autowired
	// DatainsertSerive
	
	@GetMapping("/police")
	public void InsertData() throws IOException {
		BufferedReader br  = new BufferedReader(new FileReader("C:\\Users\\User\\Desktop\\데이터\\경찰청_전국 경찰서별 강력범죄 발생 현황_2020.csv"));
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
			data.add(new PoliceStationDto(year,Policestation,murder,rob,theft,assualt,murderGrade,robGrade,theftGrade,assualtGrade));
		}
		
		
		
		
	}
	
	
	
}
