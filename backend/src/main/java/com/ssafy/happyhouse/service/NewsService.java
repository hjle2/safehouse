package com.ssafy.happyhouse.service;

import java.io.IOException;
import java.util.List;

import com.ssafy.happyhouse.dto.News;

public interface NewsService {
	List<News> getNewsList(int page) throws IOException;
}

