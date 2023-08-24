package com.timeSync.www.config.shiro;

import org.springframework.stereotype.Component;

/**
 * @author fishx
 * @version 1.0
 * @description: 存储token的媒介类
 * @date 2023/8/23 20:18
 */
@Component
public class ThreadLocalToken {
  ThreadLocal<String> local = new ThreadLocal<>();

  public void setToken(String token) {
    local.set(token);
  }

  public String getToken() {
    return local.get();
  }

  public void clear() {
    local.remove();
  }
}
