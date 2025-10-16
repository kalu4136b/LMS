package com.lms.lms.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private String secret = "mySecretKey"; // Simple key for learning

    // Create token
    public String createToken(String username, String role) {
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role) // Add role to token
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    // Get username from token
    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // Check if token is valid
    public boolean validateToken(String token, String username) {
        return getUsernameFromToken(token).equals(username);
    }
}