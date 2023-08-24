package com.timeSync.www.config.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author fishx
 * @version 1.0
 * @description: 将token封装成Shiro能识别的认证对象
 * @date 2023/8/23 19:50
 */
public class OAuth2Token implements AuthenticationToken {
  private String token;

  public OAuth2Token(String token) {
    this.token = token;
  }

  @Override
  public Object getPrincipal() {
    return token;
  }

  @Override
  public Object getCredentials() {
    return token;
  }
}
