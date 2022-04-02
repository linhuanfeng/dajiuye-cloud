package com.lhf.dajiuye.order.config;

import com.lhf.dajiuye.order.constants.SecKillMqConstants;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableRabbit
@Configuration
public class RabbitmqConsumeConfig {

    @Bean
    public Exchange orderEventExchange(){
        //String name, boolean durable, boolean autoDelete, Map<String, Object> arguments
        return new TopicExchange(
                SecKillMqConstants.ORDER_EVENT_EXCHANGE,
                true,
                false,
                null
        );
    }

    @Bean
    public Queue orderSecKillQueue(){
        /**
         * String name, boolean durable, boolean exclusive, boolean autoDelete,
         *                        @Nullable Map<String, Object> arguments
         */
        return new Queue(
                SecKillMqConstants.ORDER_SECKILL_QUEUE,
                true,
                false,
                false,
                null
        );
    }

    @Bean
    public Binding binding(){
        /**
         * String destination, DestinationType destinationType, String exchange, String routingKey,
         *                        @Nullable Map<String, Object> arguments
         */
        return new Binding(
                SecKillMqConstants.ORDER_SECKILL_QUEUE,
                Binding.DestinationType.QUEUE,
                SecKillMqConstants.ORDER_EVENT_EXCHANGE,
                SecKillMqConstants.ORDER_SECKILL_ROUTINGKEY,
                null
        );
    }
}
