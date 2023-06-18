package com.lhf.dajiuye.chat.config;

import com.hu.health.common.to.MessageDto;
import lombok.extern.slf4j.Slf4j;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 客户端连接上WebSocketServer/sendId，
 * 每次发消息就携带:个人id,目标id,消息内容
 */
@Deprecated
//@RestController
//@ServerEndpoint(value = "/WebSocketServer/{sendOpenId}")
@Slf4j
public class WebSocketServer {
    private String sendId; //当前发送者的id
    private Session session; // 与客户端得会话，通过他给客户端发送数据
    private static final Map<String,WebSocketServer> connections=new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(@PathParam("sendOpenId")String sendOpenId, Session session){
        this.sendId=sendOpenId;
        this.session=session;
        connections.put(sendOpenId,this);
        log.info("用户：{}建立了连接",sendId);
    }

    @OnClose
    public void onClose(){
        connections.remove(sendId); // 移除用户
        log.info("用户：{}断开了连接",sendId);
    }

    /**
     * 收到客户端消息
     */
    @OnMessage
    public void onMessage(String message,Session session){
        log.info("用户：{}发送了消息：{}",sendId,message);
//        MessageDto messageDto = JSON.parseObject(message, MessageDto.class);
        // 消息转发
//        try {
//            distribute(messageDto);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (EncodeException e) {
//            e.printStackTrace();
//        }
//        System.out.println(messageDto);
    }

    void distribute(MessageDto messageDto) throws IOException, EncodeException {
//        connections.get(messageDto.getToOpenId()).session.getBasicRemote().sendObject(messageDto);
        session.getBasicRemote().sendText(messageDto.toString());
    }

    @OnError
    public void onError(Session session,Throwable e){
        log.error("发送了错误：{}",e);
    }
}
