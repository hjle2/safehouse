package com.ssafy.happyhouse.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import com.ssafy.happyhouse.dto.News;

@Component
public class NewsUtil {
	final static String url = "https://search.naver.com/search.naver?where=news&sm=tab_pge&query=%EC%A3%BC%ED%83%9D%20%EB%A7%A4%EB%A7%A4&sort=0&photo=0&field=0&pd=0&ds=&de=&cluster_rank=24&mynews=0&office_type=0&office_section_code=0&news_office_checked=&nso=so:r,p:all,a:all&start=";
	final static int PAGE_MAX = 400;
	
	private int initPage(int page) {
		if (page < 1) {
			return 1;
		}
		else if (page > PAGE_MAX)
			return PAGE_MAX;
		return page;
	}
	
	public List<News> getNewsInfo(int page) {
		List<News> list = null;
		Document doc;
		page = initPage(page);
		try {
			doc = Jsoup.connect(url + ((page - 1) * 10 + 1)).get();
			
			Elements els = doc.select(".news_tit");
			
			list = new ArrayList<>();
			
			for (Element el : els) {
				News news = new News();
				news.setTitle(el.text());
				news.setLink(el.attr("href"));
				
				list.add(news);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
}
