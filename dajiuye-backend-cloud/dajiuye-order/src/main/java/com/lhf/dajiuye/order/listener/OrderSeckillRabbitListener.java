package com.lhf.dajiuye.order.listener;

import com.hu.health.common.to.SecOrderTo;
import com.lhf.dajiuye.order.constants.SecKillMqConstants;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RabbitListener(queues = {SecKillMqConstants.ORDER_SECKILL_QUEUE})
@Slf4j
public class OrderSeckillRabbitListener {
    @RabbitHandler
    public void handleOrderMessage(SecOrderTo secOrderTo, Message message, Channel channel) throws IOException {
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        log.error(secOrderTo.toString());
    }
}
