package com.it355.IT355_PZ.tool;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

public class JwtDecoder {
    private static final String SECRET_KEY = "GqNGZ8fhf5yTPK9A1C-EFGrFBjQkAVA5EaeJ5p_pGa4"; // secret key

    private static Key getKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public static String getSubject(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
}
