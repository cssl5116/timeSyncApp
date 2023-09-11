package com.timeSync.www.config.shiro;

import com.timeSync.www.entity.TbUser;
import com.timeSync.www.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Set;

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
  @Resource
  private UserService userService;


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
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection collection) {
    TbUser user = (TbUser) collection.getPrimaryPrincipal();
    int userId = user.getId();
    // 查询用户的权限列表
    Set<String> permsSet = userService.searchUserPermissions(userId);
    SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
    // 把权限列表添加到info对象中
    info.setStringPermissions(permsSet);
    return info;
  }


  /**
   * @description: 认证(登录时调用)
   * @author fishx
   * @date: 2023/8/23 20:01
   */
  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
    // 从令牌中获取userId，然后检测该账户是否被冻结。
    String accessToken = (String) token.getPrincipal();
    int userId = jwtUtils.getUserId(accessToken);
    // 查询用户信息
    TbUser user = userService.searchById(userId);
    if (user == null) throw new LockedAccountException("账号已被锁定,请联系管理员");
    // 往info对象中添加用户信息、Token字符串
    return new SimpleAuthenticationInfo(user, accessToken, getName());
  }

}
