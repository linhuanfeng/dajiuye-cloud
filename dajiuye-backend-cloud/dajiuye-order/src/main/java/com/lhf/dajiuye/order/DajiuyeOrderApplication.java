package com.lhf.dajiuye.order;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@DubboComponentScan
public class DajiuyeOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(DajiuyeOrderApplication.class, args);
    }

}
