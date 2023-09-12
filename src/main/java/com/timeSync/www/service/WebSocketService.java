package com.timeSync.www.service;

import cn.hutool.json.JSONUtil;
import com.timeSync.www.service.impl.MessageServiceImpl;
import com.timeSync.www.utils.ApplicationContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author fishx
 * @version 1.0
 * @date 2023/9/7 19:53
 */
@Component
@ServerEndpoint("/wx-api")
public class WebSocketService {
  private final Logger logger = LoggerFactory.getLogger(getClass());

  private static final AtomicInteger ONLINE_COUNT = new AtomicInteger(0);

  private static final ConcurrentHashMap<String, WebSocketService> WEBSOCKET_MAP = new ConcurrentHashMap<>();

  private Session session;

  private String sessionId;

  private int userId;

  private MessageService messageService;


  @OnOpen
  public void openConnection(Session session) {
    messageService = (MessageServiceImpl) ApplicationContextUtil.getBean("messageServiceImpl");
    this.sessionId = session.getId();
    this.session = session;
    this.userId = getUserId();
    if (WEBSOCKET_MAP.containsKey(sessionId)) {
      WEBSOCKET_MAP.remove(sessionId);
      WEBSOCKET_MAP.put(sessionId, this);
    } else {
      WEBSOCKET_MAP.put(sessionId, this);
      ONLINE_COUNT.getAndIncrement();
    }
    logger.info("用户{}连接成功, 当前用户在线人数: {}", sessionId, ONLINE_COUNT.get());
    try {
      this.sendMessage(JSONUtil.toJsonStr(messageService.refreshMessage(userId)));
    } catch (Exception e) {
      logger.error("连接异常...");
    }
  }

  @OnClose
  public void closeConnection() {
    if (WEBSOCKET_MAP.containsKey(sessionId)) {
      WEBSOCKET_MAP.remove(sessionId);
      ONLINE_COUNT.getAndDecrement();
    }
    logger.info("用户{}退出成功, 当前用户在线人数: {}", sessionId, ONLINE_COUNT.get());
  }

  @OnMessage
  public void onMessage(String message) throws IOException {
  }

  @OnError
  public void onError(Throwable error) {
    System.out.println("cause: " + error.getCause() + " ,msg: " + error.getMessage());
  }

  public void sendMessage(String message) throws IOException {
    this.session.getBasicRemote().sendText(message);
  }

  public int getUserId() {
    String name = this.session.getUserPrincipal().getName();
    String id = name.substring(name.indexOf("=") + 1, name.indexOf(","));
    return Integer.parseInt(id);
  }
}
