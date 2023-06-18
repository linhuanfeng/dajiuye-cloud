package com.hu.health.acl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.hu.health")
@MapperScan("com.hu.health.acl.mapper")
public class DajiuyeAclApplication {

    public static void main(String[] args) {
        SpringApplication.run(DajiuyeAclApplication.class, args);
    }

}
