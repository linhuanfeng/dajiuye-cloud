package com.lhf.dajiuye.own.config;

import com.lhf.dajiuye.own.interceptor.ApiIdempotentInterceptor;
import com.lhf.dajiuye.own.service.ApiIdempotentTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    ApiIdempotentTokenService apiIdempotentTokenService;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ApiIdempotentInterceptor(apiIdempotentTokenService)).addPathPatterns("/**");
    }
}
