package com.example.campus_placement_tracker.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    private final String SECRET_KEY =
            "campusplacementtrackersecretkey123456789";

    private final long EXPIRATION_TIME =
            1000 * 60 * 60; // 1 hour


    private SecretKey getKey() {

        return Keys.hmacShaKeyFor(
                SECRET_KEY.getBytes(StandardCharsets.UTF_8)
        );
    }


    // Generate JWT
    public String generateToken(String email, String role) {

        return Jwts.builder()
                .subject(email)
                .claim("role", role)
                .issuedAt(new Date())
                .expiration(
                        new Date(
                                System.currentTimeMillis()
                                        + EXPIRATION_TIME
                        )
                )
                .signWith(getKey())
                .compact();
    }


    // Get email from JWT
    public String extractEmail(String token) {

        return extractClaims(token)
                .getSubject();
    }


    // Get role from JWT
    public String extractRole(String token) {

        return extractClaims(token)
                .get("role", String.class);
    }


    // Extract all claims
    private Claims extractClaims(String token) {

        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }


    // Check whether token is valid
    public boolean isTokenValid(String token) {

        try {

            extractClaims(token);

            return true;

        } catch (Exception e) {

            return false;
        }
    }
}