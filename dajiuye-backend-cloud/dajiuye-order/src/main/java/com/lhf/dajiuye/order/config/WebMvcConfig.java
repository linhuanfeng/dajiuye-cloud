package com.lhf.dajiuye.order.config;

import com.lhf.dajiuye.order.interceptor.ApiIdempotentInterceptor;
import com.lhf.dajiuye.order.service.ApiIdempotentTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    ApiIdempotentTokenService apiIdempotentTokenService;

//    @Autowired
//    HomeService homeService;

//    @Autowired
//    CheckTokenFeignService checkTokenFeignService;

//    @Autowired
//    GetTokenByOpenIdFeignService getTokenByOpenIdFeignService;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ApiIdempotentInterceptor(apiIdempotentTokenService)).addPathPatterns("/**");
    }
}
