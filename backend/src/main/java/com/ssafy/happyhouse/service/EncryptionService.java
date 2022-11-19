package com.ssafy.happyhouse.service;

public interface EncryptionService {
	String Encryption(String password,String saltv);
	String tempPassGenerate();
	
}
