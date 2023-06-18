package com.lhf.dajiuye.own.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({CloudStorageConfig.class,WxConfig.class,ApplicationValuesConfig.class,AliYunOssConfig.class}) // 开启属性类的自动配置
public class AutoConfig {
}
