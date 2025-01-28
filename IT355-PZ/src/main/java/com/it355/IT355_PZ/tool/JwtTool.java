package com.it355.IT355_PZ.tool;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtTool {
    private static final String SECRET_KEY = "GqNGZ8fhf5yTPK9A1C-EFGrFBjQkAVA5EaeJ5p_pGa4";
    private static final long EXPIRATION_TIME = 86400000;

    private static Key getKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public static String generateToken(String subject) {
        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }
}
