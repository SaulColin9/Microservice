package com.microservice.microservicegym.controller.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.UUID;

public class CustomHttpInterceptor implements HandlerInterceptor {
    protected static final Logger logger = LoggerFactory.getLogger(CustomHttpInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        logger.info("REQUEST {}, ENDPOINT {}", request.getMethod(), request.getRequestURI());
        return true;
    }

    @Override
    public void afterCompletion(final HttpServletRequest request, final HttpServletResponse response,
                                final Object handler, final Exception ex) {
        logger.info("SERVICE RESPONSE {}, TRANSACTION ID {}", response.getStatus(), UUID.randomUUID());
    }
}