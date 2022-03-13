package com.lhf.dajiuye.own;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class DajiuyeOwnApplicationTests {

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    void contextLoads() {
        redisTemplate.opsForValue().set("test02","test02");
        Object admin = redisTemplate.opsForValue().get("test");
        System.out.println(admin);
        Object admin2 = redisTemplate.opsForValue().get("admin");
        System.out.println(admin2);
    }

}
