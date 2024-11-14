package com.np.school.management.utilities;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import com.np.school.management.entities.Users;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtility {

	private String secret = "testetsetw3t23t23gfsegsdfqer23fjsngnsdgnjghkdfgd";
	private SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));


	public String generateJwt(Users user) {
		List<String> roles = user.getRole().stream().map(role -> role.getRoleName()).toList();	
		return Jwts.builder()
				.subject(user.getUsername())
				.claim("roles",roles)
				.expiration(new Date(new Date().getTime()+30000000))
				.issuedAt(new Date())
				.signWith(key)
				.compact();

	}

	public boolean validateToken(String jwt) {
		try {
			return Jwts.parser().verifyWith(key).
					build().parseSignedClaims(jwt).getPayload()
					.getExpiration().after(new Date());
		} catch(Exception e) {
			System.out.println("Invalid token --> ValidateToken() method in util");
			return false;
		}
	}

	public String extractUsername(String jwt) {
		return Jwts.parser().verifyWith(key).build()
				.parseSignedClaims(jwt)
				.getPayload()
				.getSubject();
	}
	
	@SuppressWarnings("unchecked")
	public List<String> extractRoles(String jwt) throws Exception{
		try {
			return  (List<String>) Jwts.parser().verifyWith(key).build()
					.parseSignedClaims(jwt)
					.getPayload()
					.get("roles");
		} catch(Exception e) {
			System.out.println("Exception occurred while extracting roles: "+e.getMessage());
			throw new Exception(e.getMessage());
		}

	}
}
