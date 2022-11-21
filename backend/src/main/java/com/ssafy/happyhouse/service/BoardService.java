package com.ssafy.happyhouse.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.dto.BoardDto;
import com.ssafy.happyhouse.dto.BoardParameterDto;

public interface BoardService {
	void insert_qna(BoardDto qna)  throws SQLException;	
	List<BoardDto> select_qna()  throws SQLException;
	int BoardCnt() throws SQLException;
	void modifyArticle(BoardDto qna)  throws SQLException;
	void DeleteQna(int no)  throws SQLException;
	BoardDto get_article(int no) throws SQLException;
	List<BoardDto> SearchList(BoardParameterDto searchParam) throws SQLException;
}
