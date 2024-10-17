package com.example.config;

import com.example.exception.RestAuthenticationEntryPoint;
import com.example.filter.JwtAuthorizationFilter;
import com.example.modules.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig{

    public static final String SECRET = "admin-template";
    public static final long EXPIRATION_TIME = 10 * 24 * 60 * 60 * 1000;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String CREATE_TOKEN_URL = "/token";

    @Autowired
    UserService userService;
    @Autowired
    RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    @Autowired
    AuthenticationConfiguration authenticationConfiguration;//获取AuthenticationManager

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                // 设置会话创建策略为无状态
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // 配置异常处理
                .exceptionHandling(exception -> exception.authenticationEntryPoint(restAuthenticationEntryPoint))
                // 添加JWT认证过滤器
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), userService))
                // 配置授权规则   指定user/login路径.允许匿名访问(未登录可访问已登陆不能访问). 其他路径需要身份认证
                .authorizeHttpRequests(auth -> auth.requestMatchers(CREATE_TOKEN_URL, "/druid/**", "/swagger-ui.html",
                        "/doc.html", "/swagger-ui/*", "/swagger-resources/**", "/v2/api-docs", "/v3/api-docs", "/webjars/**",
                        "/v/img/**", "/v/upload/**", "/v/carousel/**").permitAll().anyRequest().authenticated())
                .cors((cors) -> cors.configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration configuration = new CorsConfiguration();
                        configuration.addAllowedOriginPattern("*");
                        configuration.setAllowCredentials(true);
                        configuration.addAllowedHeader("*");
                        configuration.addAllowedMethod("*");
                        configuration.addExposedHeader("*");
                        return configuration;
                    }
                }))
                // 禁用CSRF保护
                .csrf(AbstractHttpConfigurer::disable).build();


    }



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


}

