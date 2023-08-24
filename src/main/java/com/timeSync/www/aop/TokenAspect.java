package com.timeSync.www.aop;

import com.timeSync.www.config.shiro.ThreadLocalToken;
import com.timeSync.www.utils.R;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author fishx
 * @version 1.0
 * @description: TokenAspect aop切面
 * <P>拦截所有Web方法的返回值，在返回的 R对象 中添加更新后的令牌
 * @date 2023/8/24 09:40
 */
@Aspect
@Component
public class TokenAspect {
  @Resource
  private ThreadLocalToken localToken;

  @Pointcut("execution(public * com.timeSync.www.controller.*.*(..))")
  public void aspect() {
  }

  @Around("aspect()")
  public Object around(ProceedingJoinPoint point) throws Throwable {
    R r = (R) point.proceed();
    String token = localToken.getToken();
    if (token != null) {
      r.put("token", token);
      localToken.clear();
    }
    return r;
  }
}
