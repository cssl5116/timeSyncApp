package com.timeSync.www.handler;

import com.timeSync.www.exception.ConditionException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author fishx
 * @version 1.0
 * @description: 通用全局异常处理
 * @date 2023/8/24 10:11
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
  @ResponseBody
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(Exception.class)
  public String validExceptionHandler(Exception e) {
    log.error("执行异常: " + e);
    if (e instanceof MethodArgumentNotValidException) {
      MethodArgumentNotValidException exception = (MethodArgumentNotValidException) e;
      return exception.getBindingResult().getFieldError().getDefaultMessage();
    } else if (e instanceof ConditionException) {
      ConditionException exception = (ConditionException) e;
      return exception.getMsg();
    } else if (e instanceof UnauthorizedException) {
      return "暂无权限";
    } else {
      return "服务执行异常";
    }
  }
}
