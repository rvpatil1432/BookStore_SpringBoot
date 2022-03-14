package com.bridgelabz.bookstorebackend.utility;

import io.jsonwebtoken.*;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

/*
 * Utility class for jwtToken
 */
@Service
public class JwtUtil {

	@Value("${jwt.secret}")
	private String SECRET_KEY;

	// JWT token generation method
	public String createJwtToken(String email) {
		return Jwts.builder().claim("email", email).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
				.signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
	}

	// JWT token verification method
	public String verify(String token) {
		try {
			Jws<Claims> claimsJws = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
			return (String) claimsJws.getBody().get("email");
		} catch (ExpiredJwtException e) {
			return "Token Expired";
		} catch (Exception e) {
			return " Some other exception in JWT parsing ";
		}
	}

}