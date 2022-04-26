package com.lhf.dajiuye.own.service.impl;


import com.lhf.dajiuye.own.constants.IdempotentTokenConstants;
import com.lhf.dajiuye.own.service.ApiIdempotentTokenService;
import com.lhf.dajiuye.own.strategy.TokenStrategyContext;
import com.lhf.dajiuye.own.utils.SnowflakeIdGenerator;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class ApiIdempotentTokenServiceImpl implements ApiIdempotentTokenService {

    @Autowired
    private TokenStrategyContext tokenStrategyContext;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public long createToken() {
        long token = SnowflakeIdGenerator.getInstance().nextId();
        redisTemplate.opsForValue().set(IdempotentTokenConstants.API_IDEMPOTENT_TOKEN_REDIS_PREFIX+token,String.valueOf(token));
        return token;
    }

    @Override
    public void checkToken(HttpServletRequest request) {
        String token=analyzeToken(request);
        if(token==null)throw new RuntimeException("失败，未携带token");
        boolean check = tokenStrategyContext.checkToken(IdempotentTokenConstants.API_IDEMPOTENT_TOKEN_REDIS_PREFIX+token);
        if(!check){
            throw new RuntimeException("token无效或已发送成功，请勿重复发送");
        }
    }

    private String analyzeToken(HttpServletRequest request) {
        String token = request.getHeader(IdempotentTokenConstants.API_IDEMPOTENT_TOKEN_NAME);
        if(StringUtils.isEmpty(token)){
            token=request.getParameter(IdempotentTokenConstants.API_IDEMPOTENT_TOKEN_NAME);
        }
        return token;
    }
}
