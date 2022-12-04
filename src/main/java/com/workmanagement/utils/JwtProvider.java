package com.workmanagement.utils;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.workmanagement.security.CustomUserDetail;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtProvider {

	@Value("${jwt.JWT_SECRET}")
	private String signature;

	@Value("${jwt.JWT_EXPIRATION}")
	private long expiration;

	public String generateToken(Authentication authentication) {
		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + expiration);
		CustomUserDetail userDetail = (CustomUserDetail) authentication.getPrincipal();
		return Jwts.builder().setSubject(userDetail.getUsername()).setIssuedAt(now).setExpiration(expiryDate)
				.signWith(SignatureAlgorithm.HS512, signature).compact();
	}
	
	public String generateToken(String username) {
		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + expiration);
		return Jwts.builder().setSubject(username).setIssuedAt(now).setExpiration(expiryDate)
				.signWith(SignatureAlgorithm.HS512, signature).compact();
	}
	
	public String getUserNameFromJWT(String token) {
		Claims claims = Jwts.parser()
							.setSigningKey(signature)
							.parseClaimsJws(token)
							.getBody();
		return claims.getSubject();
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(signature).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
}
