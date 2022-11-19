package com.ssafy.happyhouse.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.springframework.stereotype.Service;

@Service
public class EncryptionServiceImpl implements EncryptionService{
	
	private MessageDigest mod;
	
	public EncryptionServiceImpl() throws NoSuchAlgorithmException {
		mod =  MessageDigest.getInstance("SHA-256");
	}
	
	
	@Override
	public String Encryption(String password,String saltv) {
		String value=password+  saltv;
		mod.update(value.getBytes());
		byte data[] = mod.digest();
		StringBuilder sb = new StringBuilder();
		for(byte d : data) {
			sb.append(String.format("%02x", d));
		}
		return sb.toString();
	}


	@Override
	public String tempPassGenerate() {
		final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*~";
		StringBuilder sb = new StringBuilder();
		SecureRandom random = new SecureRandom();
		int passlength = (int)(Math.random() * 10) + 5;
		for(int i=0; i < passlength; ++i) {
			int index = random.nextInt(chars.length());
			sb.append(chars.charAt(index));
		}
		
		return sb.toString();
	}

}
