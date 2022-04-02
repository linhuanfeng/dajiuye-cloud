package com.lhf.dajiuye.own.interceptor;

import com.lhf.dajiuye.own.component.ApiIdempotent;
import com.lhf.dajiuye.own.feign.CheckTokenFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

//@Component // 不能自动注入，因为创建此拦截器需要用到webmvcConfigure造成循环依赖
public class ApiIdempotentInterceptor implements HandlerInterceptor {

//    @Autowired
    CheckTokenFeignService checkTokenFeignService;

    public ApiIdempotentInterceptor(CheckTokenFeignService checkTokenFeignService){
        this.checkTokenFeignService=checkTokenFeignService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod=(HandlerMethod)handler;
        Method method = handlerMethod.getMethod();
        ApiIdempotent methodAnnotation = method.getAnnotation(ApiIdempotent.class);
        if(methodAnnotation!=null){
            check(request);
        }
        return true;
    }
    private void check(HttpServletRequest request){
        checkTokenFeignService.check(request);
    }
}
