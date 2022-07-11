package com.yi.blogj.utils;

import java.security.KeyPair;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import com.google.gson.Gson;
import com.yi.blogj.model.AccountToken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.gson.io.GsonSerializer;
import io.jsonwebtoken.security.Keys;

public class JwtUtil {

    private static final KeyPair KEY_PAIR = Keys.keyPairFor(SignatureAlgorithm.RS256);
    public static final Long ACCESS_TTL = 86400000L;
    public static final Long REFRESH_TTL = 604800000L;

    public static AccountToken create(String username, Map<String, Object> map, Long ttlMillis) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        if (ttlMillis == null) {
            ttlMillis = ACCESS_TTL;
        }
        long accessExpMillis = nowMillis + ttlMillis;
        long refreshExpMillis = nowMillis + ttlMillis;
        Date accessDate = new Date(accessExpMillis);
        Date refreshDate = new Date(refreshExpMillis);

        AccountToken accountToken = new AccountToken(
            createJwt(username, now, accessDate, map),
            createJwt(username, now, refreshDate, map),
            username
        );

        return accountToken;
    }

    private static String createJwt(String subject, Date issuedAt, Date expiration, Map<String, Object> map) {
        return Jwts.builder()
            .setClaims(map)
            .setId(UUID.randomUUID().toString())
            .setSubject(subject)
            .setIssuedAt(issuedAt)
            .setExpiration(expiration)
            .signWith(KEY_PAIR.getPrivate(), SignatureAlgorithm.RS256)
            .serializeToJsonWith(new GsonSerializer<>(new Gson()))
            .compact();
    }

    public static Claims parse(String jwt) {
        return Jwts.parserBuilder().setSigningKey(KEY_PAIR.getPublic()).build().parseClaimsJws(jwt).getBody();
    }

    public static Boolean isExpire(String jwt) {
        if (jwt == null) {
            return true;
        }
        Date expiration = parse(jwt).getExpiration();
        Date now = new Date();
        return expiration.before(now);
    }
}
