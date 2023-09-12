package com.timeSync.www.task;

import com.rabbitmq.client.*;
import com.timeSync.www.entity.MessageEntity;
import com.timeSync.www.entity.MessageRefEntity;
import com.timeSync.www.exception.ConditionException;
import com.timeSync.www.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * @author fishx
 * @version 1.0
 * @description: 消息任务类
 * - 创建的多线程任务类是用来收发 RabbitMQ消息
 * @date 2023/9/4 10:59
 */
@Slf4j
@Component
public class MessageTask {
  @Resource
  private ConnectionFactory connectionFactory;
  @Resource
  private MessageService messageService;

  /**
   * 同步发送消息
   *
   * @param topic  主题
   * @param entity 消息对象
   */
  public void send(String topic, MessageEntity entity) {
    // 向MongoDB保存消息数据，返回消息ID
    String id = messageService.insertMessage(entity);
    // 向RabbitMQ发送消息
    try {
      Connection connection = connectionFactory.newConnection();
      Channel channel = connection.createChannel();
      // 连接到某个Topic
      channel.queueDeclare(topic, true, false, false, null);
      HashMap<String, Object> header = new HashMap<>(); //存放属性数据
      header.put("messageId", id);
      // 创建AMQP协议参数对象，添加附加属性
      AMQP.BasicProperties properties = new AMQP.BasicProperties().builder().headers(header).build();
      channel.basicPublish("", topic, properties, entity.getMsg().getBytes());
      log.debug("消息发送成功");
    } catch (IOException | TimeoutException e) {
      log.error("执行异常", e);
      throw new ConditionException("向MQ发送消息失败");
    }
  }

  /**
   * 异步发送
   *
   * @param topic  主题
   * @param entity 消息对象
   */
  @Async
  public void sendAsync(String topic, MessageEntity entity) {
    send(topic, entity);
  }

  /**
   * 同步接收数据
   *
   * @param topic 主题
   * @return 接收消息数量
   */
  public Integer receive(String topic) {
    int i = 0;
    try {
      // 接收消息数据
      Connection connection = connectionFactory.newConnection();
      Channel channel = connection.createChannel();
      // 从队列中获取消息，不自动确认
      channel.queueDeclare(topic, true, false, false, null);
      // Topic中有多少条数据未知，所以使用死循环接收数据，直到接收不到消息，退出死循环
      while (true) {
        // 创建响应接收数据，禁止自动发送Ack应答
        GetResponse response = channel.basicGet(topic, false);
        if (response != null) {
          AMQP.BasicProperties props = response.getProps();
          Map<String, Object> headers = props.getHeaders();
          String messageId = headers.get("messageId").toString();
          // 获取消息正文
          byte[] message = response.getBody();
          log.debug("从RabbitMQ接收的消息: {}", message);
          MessageRefEntity messageRef = new MessageRefEntity();
          messageRef.setMessageId(messageId);
          messageRef.setReceiverId(Integer.parseInt(topic));
          messageRef.setReadFlag(false);
          messageRef.setLastFlag(true);
          // 把消息存储在MongoDB中
          messageService.insertMessageRef(messageRef);
          // 数据保存到MongoDB后,才发送Ack应答,让Topic删除这条信息
          long deliveryTag = response.getEnvelope().getDeliveryTag();
          channel.basicAck(deliveryTag, false);
          i++;
        } else {
          // 没接收到信息,则退出死循环
          break;
        }
      }
    } catch (IOException | TimeoutException e) {
      throw new RuntimeException(e);
    }
    return i;
  }

  /**
   * 异步接收数据
   *
   * @return 接收消息数量
   */
  @Async
  public Integer receiveAysnc(String topic) {
    return receive(topic);
  }

  /**
   * 同步删除消息队列
   *
   * @param topic 主题
   */
  public void deleteQueue(String topic) {
    try {
      Connection connection = connectionFactory.newConnection();
      Channel channel = connection.createChannel();
      channel.queueDelete(topic);
      log.debug("消息队列成功删除");
    } catch (IOException | TimeoutException e) {
      log.error("删除队列失败", e);
      throw new ConditionException("删除队列失败");
    }
  }

  /**
   * 异步删除消息队列
   *
   * @param topic 主题
   */
  public void deleteQueueAsync(String topic) {
    deleteQueue(topic);
  }
}
