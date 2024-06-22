package com.devdoc.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// WebMvcConfig: CORS 설정

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final long MAX_AGE_SECS = 3600; // CORS 설정의 최대 지속 시간 (1시간)

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 모든 경로에 대해 CORS 설정 적용
                // 개발환경(localhost) & 배포환경(krampoline) - URL 주소 확인
                .allowedOrigins("http://localhost:3000", "https://k72db46c526eba.user-app.krampoline.com")
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS") // 허용할 HTTP 메서드 설정
                .allowedHeaders("*") // 모든 헤더 허용
                .allowCredentials(true) // 인증 정보 허용
                .maxAge(MAX_AGE_SECS); // 설정된 시간 동안 설정 유지
    }
}
