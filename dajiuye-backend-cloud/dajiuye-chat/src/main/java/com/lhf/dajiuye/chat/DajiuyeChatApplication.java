package com.lhf.dajiuye.chat;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableRabbit
@DubboComponentScan
public class DajiuyeChatApplication {

    public static void main(String[] args) {
        SpringApplication.run(DajiuyeChatApplication.class, args);
    }

}
