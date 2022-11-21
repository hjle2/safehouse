package com.ssafy.happyhouse.dto;

public class PoliceStationDto {
	int year;
	String location;
	int murder;
	int rob;
	int theft;
	int assualt;
	int murderGrade;
	int robGrade;
	int theftGrade;
	int assualtGrade;
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getMurder() {
		return murder;
	}
	public void setMurder(int murder) {
		this.murder = murder;
	}
	public int getRob() {
		return rob;
	}
	public void setRob(int rob) {
		this.rob = rob;
	}
	public int getTheft() {
		return theft;
	}
	public void setTheft(int theft) {
		this.theft = theft;
	}
	public int getAssualt() {
		return assualt;
	}
	public void setAssualt(int assualt) {
		this.assualt = assualt;
	}
	public int getMurderGrade() {
		return murderGrade;
	}
	public void setMurderGrade(int murderGrade) {
		this.murderGrade = murderGrade;
	}
	public int getRobGrade() {
		return robGrade;
	}
	public void setRobGrade(int robGrade) {
		this.robGrade = robGrade;
	}
	public int getTheftGrade() {
		return theftGrade;
	}
	public void setTheftGrade(int theftGrade) {
		this.theftGrade = theftGrade;
	}
	public int getAssualtGrade() {
		return assualtGrade;
	}
	public void setAssualtGrade(int assualtGrade) {
		this.assualtGrade = assualtGrade;
	}
	
	public PoliceStationDto(int year, String location, int murder, int rob, int theft, int assualt, int murderGrade,
			int robGrade, int theftGrade, int assualtGrade) {
		super();
		this.year = year;
		this.location = location;
		this.murder = murder;
		this.rob = rob;
		this.theft = theft;
		this.assualt = assualt;
		this.murderGrade = murderGrade;
		this.robGrade = robGrade;
		this.theftGrade = theftGrade;
		this.assualtGrade = assualtGrade;
	}
	@Override
	public String toString() {
		return "PoliceStationDto [location=" + location + ", murder=" + murder + ", rob=" + rob + ", theft=" + theft
				+ ", assualt=" + assualt + ", murderGrade=" + murderGrade + ", robGrade=" + robGrade + ", theftGrade="
				+ theftGrade + ", assualtGrade=" + assualtGrade + "]";
	}
	
	
}
