package com.timeSync.www.config;

import com.rabbitmq.client.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author fishx
 * @version 1.0
 * @description: RabbitMQ配置类
 * @date 2023/9/4 10:47
 */
@Configuration
public class RabbitMQConfig {
  @Bean
  public ConnectionFactory getFactory() {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("127.0.0.1");
    factory.setPort(5672); //RabbitMQ端口号
    return factory;
  }
}
