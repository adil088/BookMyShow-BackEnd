package com.bookmyshow_experience.Book_my_show_experience.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.bookmyshow_experience.Book_my_show_experience.dbResponse.AppUser;
import com.bookmyshow_experience.Book_my_show_experience.services.DatabaseAPIUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

    @Value("${app.secret.key}")
    private String secretKey;
    private final Long expirationTime = 86400000L;

    // email@gmail.com
    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    // Helper method to get claims
    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractEmail(String token) {
        return getClaims(token).getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Claims claims = getClaims(token);
            return !claims.getExpiration().before(new Date()); // Check if expired
        } catch (Exception e) {
            System.err.println("Invalid JWT Token: " + e.getMessage());
            return false;
        }
    }

}
