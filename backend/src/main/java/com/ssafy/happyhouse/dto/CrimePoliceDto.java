package com.ssafy.happyhouse.dto;

public class CrimePoliceDto {
	String policestation;
	int grade;
	String addr;
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
	public CrimePoliceDto(String policestation, int grade, String addr) {
		super();
		this.policestation = policestation;
		this.grade = grade;
		this.addr = addr;
	}
	@Override
	public String toString() {
		return "CrimePoliceDto [policestation=" + policestation + ", grade=" + grade + ", addr=" + addr + "]";
	}
	
	
}
