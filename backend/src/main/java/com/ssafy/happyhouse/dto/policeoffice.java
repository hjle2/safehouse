package com.ssafy.happyhouse.dto;

public class policeoffice {
	String name;
	String addr;
	String lat;
	String lng;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}


	public policeoffice(String name, String addr, String lat, String lng) {
		super();
		this.name = name;
		this.addr = addr;
		this.lat = lat;
		this.lng = lng;
	}
	@Override
	public String toString() {
		return "policeoffice [name=" + name + ", addr=" + addr + "] ";
	}
	
	
	
}
