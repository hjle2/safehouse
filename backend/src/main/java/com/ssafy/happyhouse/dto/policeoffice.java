package com.ssafy.happyhouse.dto;

public class policeoffice {
	String name;
	String addr;
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

	public policeoffice(String name, String addr) {
		super();
		this.name = name;
		this.addr = addr;
	}
	@Override
	public String toString() {
		return "policeoffice [name=" + name + ", addr=" + addr + "] ";
	}
	
	
	
}
