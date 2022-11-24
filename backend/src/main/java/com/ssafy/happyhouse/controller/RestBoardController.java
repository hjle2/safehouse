package com.ssafy.happyhouse.controller;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dto.BoardDto;
import com.ssafy.happyhouse.dto.BoardParameterDto;
import com.ssafy.happyhouse.service.BoardService;

@RestController
@RequestMapping("/board")
@CrossOrigin("*")
public class RestBoardController {
	private final Logger logger = LoggerFactory.getLogger(RestHouseController.class);
	private BoardService bService;
	
	@Autowired
	public RestBoardController(BoardService bService) {
		this.bService = bService;
	}
	
	@GetMapping("/search")
	public ResponseEntity<?> SearchBoard(@RequestParam("searchDetail") BoardParameterDto searchParam){
			try {
				List<BoardDto> boardlist=bService.SearchList(searchParam);
				return new ResponseEntity<List<BoardDto>>(boardlist,HttpStatus.OK);
			} catch (SQLException e) {
				e.printStackTrace();
				return new ResponseEntity<List<BoardDto>>(HttpStatus.BAD_REQUEST);
			}
//
	}
	
	
	@GetMapping("/{pageNo}")
	public ResponseEntity<?> showBoard(@PathVariable int pageNo){
		logger.info("list 호출");
		try {
			List<BoardDto> boardlist =bService.select_qna(pageNo);
			System.out.println(boardlist.size());
			return new ResponseEntity<List<BoardDto>>(boardlist,HttpStatus.OK);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<List<BoardDto>>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/detail/{no}")
	public ResponseEntity<?> showDetail(@PathVariable int no){
		logger.info("detail 호출");
		try {
			BoardDto board = bService.get_article(no);
			if(board != null) return new ResponseEntity<BoardDto>(board,HttpStatus.OK);
			else return new ResponseEntity<BoardDto>(HttpStatus.NOT_FOUND);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<BoardDto>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@PostMapping()
	public ResponseEntity<?> writeBoard(@RequestBody BoardDto qna ){
		logger.info("글작성 호출");	
		try {
				bService.insert_qna(qna);
				return new ResponseEntity<Void>(HttpStatus.OK);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
			}
	}
	
	@PutMapping("/{no}")
	public ResponseEntity<?> ModifyBoard(@RequestBody BoardDto qna){
		logger.info("글수정 호출");
		try {
			bService.modifyArticle(qna);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (SQLException e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	
	@DeleteMapping("/{no}")
	public ResponseEntity<?> DeleteBoard(@PathVariable int no){
		logger.info("글삭제 호출");
		try {
			bService.DeleteQna(no);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST); 
		}
	}
	
	
	
	
	// 검색
	
	
	
	
	
	
	
	
}
