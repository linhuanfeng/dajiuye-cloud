package com.lhf.dajiuye.own.config;


import com.lhf.dajiuye.own.strategy.CheckTokenStrategy;
import com.lhf.dajiuye.own.strategy.LuaCheckTokenStrategy;
import com.lhf.dajiuye.own.strategy.TokenStrategyContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

@Configuration
public class TokenStrategyConfig {

    @Bean
    public LuaCheckTokenStrategy luaCheckTokenStrategy(StringRedisTemplate stringRedisTemplate){
        return new LuaCheckTokenStrategy(stringRedisTemplate);
    }

    @Bean
    public TokenStrategyContext tokenStrategyContext(CheckTokenStrategy checkTokenStrategy){
        return new TokenStrategyContext(checkTokenStrategy);
    }
}
