package com.lhf.dajiuye.seckill.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private String port;

    /**
     * 基于单节点的分布式锁设置
     * @return
     */
    @Bean
    public RedissonClient redissonClient(){
        Config config=new Config();
        String address="redis://"+host+":"+port;
        config.useSingleServer().setAddress(address);
        RedissonClient redissonClient = Redisson.create(config);
        return redissonClient;
    }
}
