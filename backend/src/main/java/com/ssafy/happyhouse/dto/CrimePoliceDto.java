package com.ssafy.happyhouse.dto;

public class CrimePoliceDto {
	String policestation;
	int grade;
	String addr;
	String lat;
	String lng;
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getPolicestation() {
		return policestation;
	}
	public void setPolicestation(String policestation) {
		this.policestation = policestation;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	public CrimePoliceDto(String policestation, int grade, String addr, String lat, String lng) {
		super();
		this.policestation = policestation;
		this.grade = grade;
		this.addr = addr;
		this.lat = lat;
		this.lng = lng;
	}
	@Override
	public String toString() {
		return "CrimePoliceDto [policestation=" + policestation + ", grade=" + grade + ", addr=" + addr + "]";
	}
	
	
}
