package com.ssafy.happyhouse.dto;

public class HouseDeal {
	private int aptCode;
	private int cnt;
	private String aptName;
	private int dealAmount;
	private String lat;
	private String lng;

	public HouseDeal() {
		super();
	}

	public HouseDeal(int aptCode, int cnt, String aptName, int dealAmount, String lat, String lng) {
		super();
		this.aptCode = aptCode;
		this.cnt = cnt;
		this.aptName = aptName;
		this.dealAmount = dealAmount;
		this.lat = lat;
		this.lng = lng;
	}

	public int getAptCode() {
		return aptCode;
	}

	public void setAptCode(int aptCode) {
		this.aptCode = aptCode;
	}

	public String getAptName() {
		return aptName;
	}

	public void setAptName(String aptName) {
		this.aptName = aptName;
	}

	public int getDealAmount() {
		return dealAmount;
	}

	public void setDealAmount(int dealAmount) {
		this.dealAmount = dealAmount;
	}

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

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	@Override
	public String toString() {
		return "HouseDeal [aptCode=" + aptCode + ", cnt=" + cnt + ", aptName=" + aptName + ", dealAmount=" + dealAmount
				+ ", lat=" + lat + ", lng=" + lng + "]";
	}

}
