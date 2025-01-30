package com.bookmyshow_experience.Book_my_show_experience.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.bookmyshow_experience.Book_my_show_experience.dbResponse.AppUser;
import com.bookmyshow_experience.Book_my_show_experience.services.DatabaseAPIUtil;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

    @Autowired
    DatabaseAPIUtil databaseAPIUtil;

    @Value("${app.secret.key}")
    private String key;
    private final Long expirationTime = 3600000000000l;

    // email@gmail.com:pass@123
    public String generateToken(String credentials) {
        return Jwts.builder()
                .setSubject(credentials)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }

    public String extractCredentials(String token) {
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            // is this token valid or not?
            // extract credentials
            String credentials = this.extractCredentials(token);
            String email = credentials.split(":")[0];
            String password = credentials.split(":")[1];

            System.out.println("Inside validate token");
            // database call
            AppUser user = databaseAPIUtil.getUserByEmail(email);

            if (user == null) {
                return false;
            }

            if (!user.getPassword().equals(password))
                return false;
            return true;
        } catch (Exception e) {
            throw e;
        }
    }
}
