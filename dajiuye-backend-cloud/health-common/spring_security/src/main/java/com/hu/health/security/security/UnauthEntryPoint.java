package com.hu.health.security.security;


import com.hu.health.base.utils.R;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import com.hu.health.base.utils.ResponseUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证入口点，处理用户请求过程遇到困难
 * 用户请求了一个受保护的资源，但是用户没有通过认证，那么抛出异常，AuthenticationEntryPoint. Commence(..)就会被调用
 */
public class UnauthEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        ResponseUtil.out(httpServletResponse, R.error(e.getMessage()+e.getStackTrace()));
    }
}
