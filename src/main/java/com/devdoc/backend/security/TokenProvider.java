package com.devdoc.backend.security;

import com.devdoc.backend.model.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;


// TokenProvider: JWT 토큰을 생성하고 검증

@Slf4j
@Service
public class TokenProvider {

    @Value("${jwt.secret}")
    private String secretKey;

    // 사용자 정보를 바탕으로 JWT 토큰 생성
    public String create(UserEntity userEntity) {
        Date expiryDate = Date.from(
                Instant.now()
                        .plus(1, ChronoUnit.DAYS)); // 만료 시간 설정

        // 토큰 빌더
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, secretKey) // 서명 알고리즘 및 비밀키 설정
                .setSubject(userEntity.getId()) // 사용자 ID를 주제로 설정
                .setIssuer("demo app") // 발행자 설정
                .setIssuedAt(new Date()) // 발행 시간 설정
                .setExpiration(expiryDate) // 만료 시간 설정
                .compact(); // 토큰 생성
    }

    // 토큰을 검증하고 사용자 ID를 반환
    public String validateAndGetUserId(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey) // 비밀키 설정
                .parseClaimsJws(token) // 토큰 파싱
                .getBody(); // 클레임 추출

        return claims.getSubject(); // 주제 반환
    }
}