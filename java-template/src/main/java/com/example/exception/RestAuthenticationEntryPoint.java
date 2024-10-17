package com.example.exception;

import cn.hutool.http.HttpStatus;
import cn.hutool.json.JSONUtil;
import com.example.result.Result;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setStatus(HttpStatus.HTTP_UNAUTHORIZED);
        response.getWriter().println(JSONUtil.parse(Result.build().setCode(ExceptionType.UNAUTHORIZED.getCode()).setMessage(ExceptionType.UNAUTHORIZED.getMessage())));
        response.getWriter().flush();
    }
}
