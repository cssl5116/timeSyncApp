package com.timeSync.www.config.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author fishx
 * @version 1.0
 * @description: 定义认证与授权的实现方法
 * @date 2023/8/23 19:56
 */
@Component
public class OAuth2Realm extends AuthorizingRealm {
  @Resource
  private JwtUtils jwtUtils;

  @Override
  public boolean supports(AuthenticationToken token) {
    return token instanceof OAuth2Token;
  }

  /**
   * @description: 授权(验证权限时调用)
   * @author fishx
   * @date: 2023/8/23 20:00
   */
  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
    SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
    // TODO: 查询用户的权限列表
    // TODO: 把权限列表添加到info对象中
    return info;
  }


  /**
   * @description: 认证(登录时调用)
   * @author fishx
   * @date: 2023/8/23 20:01
   */
  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
    // TODO: 从令牌中获取userId，然后检测该账户是否被冻结。
    SimpleAuthenticationInfo info = new SimpleAuthenticationInfo();
    // TODO: 往info对象中添加用户信息、Token字符串
    return info;
  }
}
