package com.timeSync.www.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author fishx
 * @version 1.0
 * @description: WebSocket配置类
 * @date 2023/9/7 19:49
 */
//@Configuration
public class WebSocketConfig {
  @Bean
  public ServerEndpointExporter serverEndpointExporter() {
    return new ServerEndpointExporter();
  }
}
