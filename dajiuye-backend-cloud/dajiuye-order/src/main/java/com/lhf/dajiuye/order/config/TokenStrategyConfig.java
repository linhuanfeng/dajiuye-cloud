package com.lhf.dajiuye.order.config;


import com.lhf.dajiuye.order.strategy.CheckTokenStrategy;
import com.lhf.dajiuye.order.strategy.LuaCheckTokenStrategy;
import com.lhf.dajiuye.order.strategy.TokenStrategyContext;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
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
