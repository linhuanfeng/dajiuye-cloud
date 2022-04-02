package com.lhf.dajiuye.seckill.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@Slf4j
public class RabbitConfirmConfig {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void setConfirmCallBack(){
        // 到达broker的回调
        //  CorrelationData correlationData, boolean ack, @Nullable String cause
        rabbitTemplate.setConfirmCallback((correlationData,ack,cause)->{
            if(ack){
                log.error("成功setConfirmCallBack");
            }else {
                log.error("失败setConfirmCallBack");
            }
        });
        // 到达队列失败才返回
        // ReturnedMessage
        rabbitTemplate.setReturnsCallback((retMsg)->{
            log.error("成功到达队列{}",retMsg.getMessage());
        });
    }
}
