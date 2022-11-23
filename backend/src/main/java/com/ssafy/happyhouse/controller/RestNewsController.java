package com.ssafy.happyhouse.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dto.News;
import com.ssafy.happyhouse.service.NewsService;

@RestController
@CrossOrigin("*")
public class RestNewsController {

    private NewsService service;
	
    
    @Autowired
	public RestNewsController(NewsService service) {
		super();
		this.service = service;
	}
    
	@GetMapping("/news/{page}")
	public ResponseEntity<?> showNews(@PathVariable("page") String page) {
		int p = Integer.parseInt(page);
		try {
			List<News> newss = service.getNewsList(p);
			if (newss != null && !newss.isEmpty()) {
				return new ResponseEntity<List<News>>(newss, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
