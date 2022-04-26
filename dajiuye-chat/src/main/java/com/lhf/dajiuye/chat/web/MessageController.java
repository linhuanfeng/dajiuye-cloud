package com.lhf.dajiuye.chat.web;

import com.lhf.dajiuye.chat.constants.WebSocketRabbitConstants;
import com.hu.health.common.to.MessageDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * messagingTemplate.convertAndSendToUser
 * messagingTemplate.convertAndSend 本质都是一样的
 */
@RestController
@Slf4j
@RequestMapping("/chat")
public class MessageController {

    //spring提供的推送方式
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Resource
    private RabbitTemplate rabbitTemplate;

    @RequestMapping("/publicnotice")
    public void publicNotice(@RequestBody MessageDto requestMsg){
        log.info("系统通知：{}",requestMsg);
        messagingTemplate.convertAndSend("/topic/public",requestMsg.getContent()+"--来自"+requestMsg.getFromOpenId());
        // 消息异步持久化到数据库
        rabbitTemplate.convertAndSend(WebSocketRabbitConstants.MESSAGE_TOPIC_EXCHANGE,WebSocketRabbitConstants.MESSAGE_PUBLIC_KEY,requestMsg);
    }

    @RequestMapping("/privatenotice")
    public void privateNotice(@RequestBody MessageDto requestMsg){
        log.info("私人通知：{}",requestMsg);
        messagingTemplate.convertAndSendToUser(requestMsg.getToOpenId(),"/private",requestMsg);
        // 消息异步持久化到数据库
        rabbitTemplate.convertAndSend(WebSocketRabbitConstants.MESSAGE_TOPIC_EXCHANGE,WebSocketRabbitConstants.MESSAGE_PUBLIC_KEY,requestMsg);
    }

    /**
     * 广播模式 d
     * @param requestMsg
     * @return
     */
    @MessageMapping("/public")
    public void broadcast(MessageDto requestMsg) {
        log.info("public收到：{}",requestMsg);
        //这里是有return，如果不写@SendTo默认和/topic/broadcast一样
        messagingTemplate.convertAndSend("/topic/public",requestMsg.getContent()+"--来自"+requestMsg.getFromOpenId());
//        rabbitTemplate.convertAndSend(WebSocketRabbitConstants.MESSAGE_TOPIC_EXCHANGE,
//                WebSocketRabbitConstants.MESSAGE_TOPIC_KEY,requestMsg);
        // 消息异步持久化到数据库
        rabbitTemplate.convertAndSend(WebSocketRabbitConstants.MESSAGE_TOPIC_EXCHANGE,WebSocketRabbitConstants.MESSAGE_PUBLIC_KEY,requestMsg);
    }

    /**
     * 用户模式
     * @param requestMsg
     */
    @MessageMapping("/private")
    //@SendToUser("/queue/one") 如果存在return,可以使用这种方式
    public void one(MessageDto requestMsg) {
        log.info("private收到：{}",requestMsg);
        // 默认会拼接：user/to/message 发给个人
        // super.convertAndSend(this.destinationPrefix + user + destination, payload, headers, postProcessor);
        messagingTemplate.convertAndSendToUser( requestMsg.getToOpenId(),"private", requestMsg);
        // 消息异步持久化到数据库
        rabbitTemplate.convertAndSend(WebSocketRabbitConstants.MESSAGE_TOPIC_EXCHANGE,WebSocketRabbitConstants.MESSAGE_PRIVATE_KEY,requestMsg);
    }
}