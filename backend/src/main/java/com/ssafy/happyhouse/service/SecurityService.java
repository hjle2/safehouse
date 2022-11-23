package com.ssafy.happyhouse.service;

public interface SecurityService {
	String Encryption(String password,String saltv);
	String tempPassGenerate();


}
