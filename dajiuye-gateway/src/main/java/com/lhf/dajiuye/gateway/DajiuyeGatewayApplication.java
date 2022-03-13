package com.lhf.dajiuye.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class DajiuyeGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(DajiuyeGatewayApplication.class, args);
    }

}
