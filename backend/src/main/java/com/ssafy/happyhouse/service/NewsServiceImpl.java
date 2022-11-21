package com.ssafy.happyhouse.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dto.News;
import com.ssafy.happyhouse.util.NewsUtil;

@Service
public class NewsServiceImpl implements NewsService {

	private NewsUtil newsUtil;
	
	public NewsServiceImpl(NewsUtil newsUtil) {
		super();
		this.newsUtil = newsUtil;
	}
	
	@Override
	public List<News> getNewsList(int page) throws IOException {
		return newsUtil.getNewsInfo(page);
	}
}
