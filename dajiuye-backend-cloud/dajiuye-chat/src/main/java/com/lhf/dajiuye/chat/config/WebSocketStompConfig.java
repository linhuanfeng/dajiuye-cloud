package com.lhf.dajiuye.chat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * WebSocket配置类
 */
@Deprecated
//@Configuration
public class WebSocketStompConfig {

    /**
     * ServerEndpointExporter配置类
     *  用来检测ServerEndpointConfig，
     *  注册WebSocket运行时环境
     *  并且扫描ServerEndpoint
     * @return
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }
}
