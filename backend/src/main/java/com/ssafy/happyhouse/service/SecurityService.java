package com.ssafy.happyhouse.service;

import java.util.Map;

import com.ssafy.happyhouse.dto.TokenDto;
import com.ssafy.happyhouse.dto.User;

public interface SecurityService {
	String Encryption(String password,String saltv);
	String tempPassGenerate();
	TokenDto generateJwToken(User user);
	Map<String,Object> CreateTokenHeader();
	Map<String,Object> CreateClaims(User user);
	

}
