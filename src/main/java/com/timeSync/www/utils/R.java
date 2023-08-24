package com.timeSync.www.utils;

import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * @author fishx
 * @version 1.0
 * @description: 统一返回对象
 * @date 2023/8/23 14:47
 */
public class R extends HashMap<String, Object> {
  public R() {
    put("code", HttpStatus.SC_OK);
    put("msg", "success");
  }

  /**
   * @description: 链式调用put方法
   * @param: k-v
   * @return: this
   * @author fishx
   * @date: 2023/8/23 14:59
   */
  public R put(String k, Object v) {
    super.put(k, v);
    return this;
  }

  public static R ok() {
    return new R();
  }

  public static R ok(String msg) {
    return new R().put("msg", msg);
  }

  public static R ok(Map<String, Object> map) {
    R r = new R();
    r.putAll(map);
    return r;
  }

  public static R error(int code, String msg) {
    return new R().put("code", code).put("msg", msg);
  }

  public static R error() {
    return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "未知异常,请联系管理员");
  }

  public static R error(String msg) {
    return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
  }
}
