package com.example.new_order.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // 환경설정, 서버가 구동될 때 실행하여 오류가 있으면 서버가 중지됨
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.httpBasic(AbstractHttpConfigurer::disable);

        http.authorizeHttpRequests((authorize) -> 
            authorize.requestMatchers("/admin", "/admin/*").hasRole("ADMIN")
            .requestMatchers("/employee", "/employee/*").hasRole("EMPLOYEE")
            .anyRequest().permitAll()
        );

        // 세션 생성 정책 설정
        http.sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS));

        // 접근 권한 불가 페이지 설정
        http.exceptionHandling((exceptionHandling) -> exceptionHandling.accessDeniedPage("/page403.do"));

        // 특정 경로에 대해 CSRF 보호 해제
        http.csrf((csrf) -> csrf.ignoringRequestMatchers("/api/**"));

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
