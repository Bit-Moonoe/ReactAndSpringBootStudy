package com.example.demo.security;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.example.demo.model.UserEntity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class TokenProvider {
	
	private static final String SECRET_KEY = "ansgudrlsmscjsworoqkfsdaswkrkehlfrjek7377";
	
	//토큰 발행
	public String create(UserEntity userEntity) {
		//기한을 지금으로부터 1일로 설정
		Date expiryDate = Date.from(Instant.now().plus(1, ChronoUnit.DAYS));
		
		return Jwts.builder()
				.signWith(SignatureAlgorithm.HS512, SECRET_KEY)
				.setSubject(userEntity.getId())
				.setIssuer("demp app")
				.setIssuedAt(new Date())
				.setExpiration(expiryDate)
				.compact();
		
		
	}
	
	public String validateAndGetUserId(String token) {
		Claims claims = Jwts.parser()
				.setSigningKey(SECRET_KEY)
				.parseClaimsJws(token)
				.getBody();
		
		return claims.getSubject();
	}
}
