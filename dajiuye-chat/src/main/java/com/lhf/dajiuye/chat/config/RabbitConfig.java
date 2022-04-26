package com.lhf.dajiuye.chat.config;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.lhf.dajiuye.chat.constants.WebSocketRabbitConstants.*;

@Configuration
@Slf4j
public class RabbitConfig  {

    @Bean
    public Queue topicQueue(){
        return new Queue(MESSAGE_TOPIC_QUEUE,true);
    }

    @Bean
    TopicExchange exchange(){
        return new TopicExchange(MESSAGE_TOPIC_EXCHANGE,true,false);
    }

    @Bean
    Binding binding(){
        return BindingBuilder.bind(topicQueue()).to(exchange()).with(MESSAGE_TOPIC_KEY);
    }

//    @Bean
    public ConnectionFactory connectionFactory(){
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("192.168.42.100", 5672);
        connectionFactory.setUsername("itcast");
        connectionFactory.setPassword("123");
        connectionFactory.setVirtualHost("/");
        connectionFactory.setPublisherConfirmType(CachingConnectionFactory.ConfirmType.CORRELATED); // 发送消息回调
        connectionFactory.setPublisherReturns(true);
        return connectionFactory;
    }

//    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        rabbitTemplate.setMandatory(true); // 开启手动Mandatory才能触发回调函数
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                log.info("相关数据correlationData：{}",correlationData);
                log.info("确认情况ack：{}",ack);
                log.info("原因cause：{}",cause);
            }
        });
        rabbitTemplate.setReturnsCallback(new RabbitTemplate.ReturnsCallback() {
            @Override
            public void returnedMessage(ReturnedMessage returned) {
                log.info("消息："+returned.getMessage());
                log.info("回应码："+returned.getReplyCode());
                log.info("回应信息："+returned.getReplyText());
                log.info("交换机："+returned.getExchange());
                log.info("路由键："+returned.getRoutingKey());
            }
        });
        return rabbitTemplate;
    }

    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

//    @Bean
    public SimpleMessageListenerContainer messageListenerContainer(){
        SimpleMessageListenerContainer listenerContainer = new SimpleMessageListenerContainer(connectionFactory());
        listenerContainer.setQueues(topicQueue());
        listenerContainer.setExposeListenerChannel(true);
        listenerContainer.setMaxConcurrentConsumers(1);
        listenerContainer.setConcurrentConsumers(1);
        listenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        listenerContainer.setMessageListener(new ChannelAwareMessageListener() {
            @Override
            public void onMessage(Message message, Channel channel) throws Exception {
                byte[] body = message.getBody();
                String msg = new String(body);
                log.info("rabbitmq接受到消息：{}",msg);
                channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
            }
        });
        return listenerContainer;
    }

//    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        RabbitTemplate rabbitTemplate = applicationContext.getBean(RabbitTemplate.class);
        rabbitTemplate.setConfirmCallback(((correlationData, ack, cause) -> {
            if(ack){
                log.info("ack");
            }else {
                log.error("失败：{}",new String(cause.getBytes()));
            }
        }));
        // void returnedMessage(ReturnedMessage returned);
        rabbitTemplate.setReturnsCallback((returnedMessage ->
                log.error("",returnedMessage.getReplyCode(), returnedMessage.getReplyText(),
                        returnedMessage.getMessage(),
                        returnedMessage.getExchange(),
                        returnedMessage.getRoutingKey())));
    }
}
