package com.timeSync.www.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @author fishx
 * @version 1.0
 * @description: 自定义异常类
 * @date 2023/8/23 14:14
 */
@Getter
@Setter
public class ConditionException extends RuntimeException {
  private String msg;
  private int code = 500;

  public ConditionException(String msg) {
    super(msg);
    this.msg = msg;
  }

  public ConditionException(String msg, Throwable e) {
    super(msg, e);
    this.msg = msg;
  }

  public ConditionException(int code, String msg) {
    super(msg);
    this.msg = msg;
    this.code = code;
  }

  public ConditionException(int code, String msg, Throwable e) {
    super(msg, e);
    this.msg = msg;
    this.code = code;
  }
}
