package com.ssafy.happyhouse.dto;

public class News {
	
	private String title;
	private String link;
	
	public News() {
		super();
	}
	
	public News(String title, String link) {
		super();
		this.title = title;
		this.link = link;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getLink() {
		return link;
	}
	
	public void setLink(String link) {
		this.link = link;
	}
}
