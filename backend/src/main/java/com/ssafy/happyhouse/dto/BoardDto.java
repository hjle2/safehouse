package com.ssafy.happyhouse.dto;




public class BoardDto {

	private int no;

	private String id;

	private String title;

	private String content;

	private String regtime;

	public int getNo() {
		return no;
	}



	public void setNo(int no) {
		this.no = no;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
	}



	public String getRegtime() {
		return regtime;
	}



	public void setRegtime(String regtime) {
		this.regtime = regtime;
	}



	@Override
	public String toString() {
		return "BoardDto [no=" + no + ", id=" + id + ", title=" + title + ", content=" + content + ", regtime="
				+ regtime + "]";
	}




}