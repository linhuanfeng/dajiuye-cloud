package com.lhf.dajiuye.order.strategy;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import java.util.ArrayList;
import java.util.List;

/**
 * 具体策略角色
 */
public class LuaCheckTokenStrategy extends CheckTokenStrategy{
    private StringRedisTemplate stringRedisTemplate;

    public LuaCheckTokenStrategy(StringRedisTemplate stringRedisTemplate){
        this.stringRedisTemplate=stringRedisTemplate;
    }

    private final String LUA_DEL_TOKEN="if redis.call('get',KEYS[1])==ARGV[1] " +
            "then return redis.call('del',KEYS[1]) " +
            "else return 0 end";

    public boolean checkToken(String token){
        List<String> keyList=new ArrayList<>(1);
        keyList.add(token);
        Long execute = stringRedisTemplate.execute(new DefaultRedisScript<>(LUA_DEL_TOKEN, Long.class),
                keyList, token);
//        return execute.
        return execute.longValue()!=0;
    }
}
