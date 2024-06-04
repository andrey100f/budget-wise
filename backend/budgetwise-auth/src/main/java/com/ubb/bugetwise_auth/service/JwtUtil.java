package com.ubb.bugetwise_auth.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@Service
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private String expiration;

    private Key key;

    @PostConstruct
    public void initKey() {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public Claims getClaims(String token) {
        return Jwts.parser().setSigningKey(key).build().parseClaimsJwt(token).getBody();
    }

    public Date getExpirationDate(String token) {
        return this.getClaims(token).getExpiration();
    }

    public String generate(String id, String role, String username, String type) {
        Map<String, String> claims = Map.of("id", id, "role", role);

        long expirationDate = "ACCESS".equalsIgnoreCase(type)
            ? Long.parseLong(expiration) * 1000
            : Long.parseLong(expiration) * 1000 * 5;

        final Date now = new Date();
        final Date exp = new Date(now.getTime() + expirationDate);

        return Jwts.builder()
            .setClaims(claims)
            .setSubject(claims.get("id"))
            .setIssuedAt(now)
            .setExpiration(exp)
            .signWith(key)
            .compact();
    }

}
