package com.ssafy.happyhouse.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dto.BoardDto;
import com.ssafy.happyhouse.dto.BoardParameterDto;
import com.ssafy.happyhouse.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService{

	BoardMapper bMapper;
	
	@Autowired
	public BoardServiceImpl(BoardMapper bMapper) {
		this.bMapper = bMapper;
	}
	
	
	
	
	@Override
	public void insert_qna(BoardDto qna) throws SQLException {
		bMapper.writeArticle(qna);
	}

	@Override
	public List<BoardDto> select_qna(int pageNo) throws SQLException {
		return bMapper.listArticle((pageNo-1)*10);
	}

	@Override
	public int BoardCnt() throws SQLException {
		// TODO Auto-generated method stub
		return -1;
	}

	@Override
	public void modifyArticle(BoardDto qna) throws SQLException {
		bMapper.modifyArticle(qna);
	}

	@Override
	public void DeleteQna(int no) throws SQLException {
		bMapper.deleteArticle(no);
	}

	public List<BoardDto> SearchList(BoardParameterDto searchParam) throws SQLException{
		return bMapper.SearchArticle(searchParam);
	}

	@Override
	public BoardDto get_article(int no) throws SQLException {
		
		return bMapper.getArticle(no);
	}

}
