package com.hu.health.security.filter;

import com.hu.health.base.exception.BizCodeEnum;
import com.hu.health.base.utils.R;
import com.hu.health.base.utils.ResponseUtil;
import com.hu.health.security.entity.SecurityUser;
import com.hu.health.security.entity.User;
import com.hu.health.security.security.TokenManager;
import com.fasterxml.jackson.databind.ObjectMapper;
//import org.apache.tomcat.util.http.ResponseUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 认证登录 用户名和密码，
 *      （1）登录成功，生成token和 把token对应的权限信息保存到redis中
 *      （2）可以自定义登录路径
 * 继承UsernamePasswordAuthFilter,重写successfulAuthentication认证成功逻辑，即保存token到redis
 */
public class MyUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private TokenManager tokenManager;
    private RedisTemplate redisTemplate;
    // 认证管理器
    private AuthenticationManager authenticationManager;

    public MyUsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager, TokenManager tokenManager, RedisTemplate redisTemplate) {
        this.authenticationManager = authenticationManager;
        this.tokenManager = tokenManager;
        this.redisTemplate = redisTemplate;
        this.setPostOnly(false);
        // 设置拦截登录的url匹配器，不然默认/login
        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/acl/login","POST"));
    }

    //1 获取表单提交用户名和密码,认证成功则包装成一个Authentication认证对象
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        //获取表单提交数据
        try {
            User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword(),
                    new ArrayList<>()));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    //2 认证成功调用的方法
    @Override
    protected void successfulAuthentication(HttpServletRequest request, 
                                            HttpServletResponse response, FilterChain chain, Authentication authResult)
            throws IOException, ServletException {
        //认证成功，得到认证成功之后用户信息
        SecurityUser user = (SecurityUser)authResult.getPrincipal();
        //根据用户名生成token
        String token = tokenManager.createToken(user.getCurrentUserInfo().getUsername());
        //把用户名称和用户权限列表放到redis
        redisTemplate.opsForValue().set(user.getCurrentUserInfo().getUsername(),user.getPermissionValueList());
        //返回token
        ResponseUtil.out(response, R.ok().put("token",token));
    }

    //3 认证失败调用的方法
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed)
            throws IOException, ServletException {
        ResponseUtil.out(response, R.error(BizCodeEnum.AUTH_USERNAME_PASSWORD_EXCEPTION.getCode(),BizCodeEnum.AUTH_USERNAME_PASSWORD_EXCEPTION.getMsg()));
    }
}
