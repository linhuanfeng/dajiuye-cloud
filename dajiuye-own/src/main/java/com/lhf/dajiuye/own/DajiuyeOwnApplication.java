package com.lhf.dajiuye.own;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.hu.health","com.lhf.dajiuye.own","com.lhf.dajiuye.own.feign"}) // 不然security扫描不到
@EnableFeignClients("com.lhf.dajiuye.own.feign")
@EnableDiscoveryClient
public class DajiuyeOwnApplication {

    public static void main(String[] args) {
        SpringApplication.run(DajiuyeOwnApplication.class, args);
    }

}
