package com.ssafy.happyhouse.service;

import java.io.IOError;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dto.TokenDto;
import com.ssafy.happyhouse.dto.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class SecurityServiceImpl implements SecurityService{
	
	private MessageDigest mod;
	private final String SecurityKey = "Seoul13";
	private final Long AccessTokenexpiredPeriod = 1000* 60L * 60L * 3L;
	private final Long RefreshTokenExpiredPeriod = 14 * 24 * 60 * 60 * 1000L;
	public SecurityServiceImpl() throws NoSuchAlgorithmException {
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

	
	//jwt functions
	/*
	@Override
	public TokenDto generateJwToken(User user) {
		Date dateD = new Date();
		
		String accessToken = Jwts.builder()
				.setSubject(user.getName())
				.setHeader(this.CreateTokenHeader())
				.setClaims(this.CreateClaims(user))
				.setExpiration(new Date(dateD.getTime() + AccessTokenexpiredPeriod))
				.setSubject("accessToken")
				.signWith(SignatureAlgorithm.HS256,SecurityKey).compact();
		String refreshToken = Jwts.builder()
				.setExpiration(new Date(dateD.getTime() + RefreshTokenExpiredPeriod))
				.setSubject("refreshToken")
				.signWith(SignatureAlgorithm.HS256,SecurityKey).compact();
				
		
		return new TokenDto(accessToken,refreshToken);
	}

	public Claims DecodingAccessToken(String token) {
		try {
			Claims claims = Jwts.parser()
					.setSigningKey(SecurityKey)
					.parseClaimsJws(token)
					.getBody();
			
			return claims;
		} catch(ExpiredJwtException e) {
			return e.getClaims();
		}
	}
	
	public boolean validationToken(String token) {
		try {
		Claims claims = Jwts.parser()
				.setSigningKey(SecurityKey)
				.parseClaimsJws(token)
				.getBody();
		} catch(ExpiredJwtException e) {
			return false;
		} 
		return true;
	}
	
	public String ParseClaims(String accessToken) {
		return null;
	}

	@Override
	public Map<String, Object> CreateTokenHeader() {
		Map<String, Object> header = new HashMap<>();
		header.put("typ", "JWT");
		header.put("alg", "HS256");
		header.put("regDate", System.currentTimeMillis());
		return header;
	}


	@Override
	public Map<String, Object> CreateClaims(User user) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("username", user.getName());
		claims.put("roles", user.getTel());
		return claims;
	}
	*/
	//토큰 검증

}
