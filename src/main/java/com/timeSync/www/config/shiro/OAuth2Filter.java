package com.timeSync.www.config.shiro;

import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import org.apache.http.HttpStatus;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author fishx
 * @version 1.0
 * @description: 因为在 OAuth2Filter 类中要读写 ThreadLocal 中的数据，
 * 所以 OAuth2Filter 类必须要设置成多例的，否则 ThreadLocal 将无法使用。
 * @date 2023/8/23 20:22
 */
@Component
@Scope("prototype")
public class OAuth2Filter extends AuthenticatingFilter {
  @Resource
  private ThreadLocalToken localToken;
  @Value("${time-sync.jwt.cache-expire}")
  private int cacheExpire;
  @Resource
  JwtUtils jwtUtils;
  @Resource
  private StringRedisTemplate stringRedisTemplate;

  // 拦截请求之后，用于把令牌字符串封装成令牌对象
  @Override
  protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {
    String token = getRequestToken((HttpServletRequest) request);
    if (StrUtil.isBlank(token)) {
      return null;
    }
    return new OAuth2Token(token);
  }

  // 拦截请求，判断请求是否需要被Shiro处理
  @Override
  protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
    HttpServletRequest req = (HttpServletRequest) request;
    // Ajax提交application/json数据的时候，会先发出Options请求
    // 直接放行Options请求,其他都需shiro处理
    return req.getMethod().equals(RequestMethod.OPTIONS.name());
  }

  // 该方法用于处理所有应该被Shiro处理的请求
  @Override
  protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    HttpServletResponse response = (HttpServletResponse) servletResponse;
    response.setHeader("Content-Type", "text/html;charset=UTF-8");
    // 允许跨域请求
    response.setHeader("Access-Control-Allow-Credentials", "true");
    response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
    localToken.clear();
    // 获取请求token，如果token不存在，直接返回401
    String token = getRequestToken(request);
    if (StrUtil.isEmpty(token)) {
      response.setStatus(HttpStatus.SC_UNAUTHORIZED);
      response.getWriter().print("无效的令牌");
      return false;
    }
    // 检查令牌是否过期
    try {
      jwtUtils.verifierToken(token);
    } catch (TokenExpiredException e) {
      if (BooleanUtil.isTrue(stringRedisTemplate.hasKey(token))) {
        // 客户端token过期,Redis存储token存在 => 为客户端免密续签token
        // 删除Redis缓存
        stringRedisTemplate.delete(token);
        int userId = jwtUtils.getUserId(token);
        // 生成新的令牌
        token = jwtUtils.createToken(userId);
        stringRedisTemplate.opsForValue().set(token, String.valueOf(userId), cacheExpire, TimeUnit.DAYS);
        // 把新令牌绑定到线程
        localToken.setToken(token);
      } else {
        // Redis也过期 => 要求重新登录
        response.setStatus(HttpStatus.SC_UNAUTHORIZED);
        response.getWriter().print("token已过期");
        return false;
      }
    } catch (JWTDecodeException e) {
      response.setStatus(HttpStatus.SC_UNAUTHORIZED);
      response.getWriter().print("无效token");
      return false;
    }
    return executeLogin(request, response);
  }

  @Override
  protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest servletRequest, ServletResponse servletResponse) {
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    HttpServletResponse response = (HttpServletResponse) servletResponse;
    response.setHeader("Content-Type", "text/html;charset=UTF-8");
    // 允许跨域请求
    response.setHeader("Access-Control-Allow-Credentials", "true");
    response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
    response.setStatus(HttpStatus.SC_UNAUTHORIZED);
    try {
      response.getWriter().print(e.getMessage());
    } catch (IOException ex) {
      throw new RuntimeException(ex);
    }
    return false;
  }

  @Override
  public void doFilterInternal(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
    super.doFilterInternal(request, response, chain);
  }

  private String getRequestToken(HttpServletRequest request) {
    String token = request.getHeader("token");
    if (StrUtil.isBlank(token)) {
      // 请求头中没有,尝试从请求体获取
      token = request.getParameter("token");
    }
    return token;
  }
}
