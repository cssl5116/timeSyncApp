package com.timeSync.www.config.xss;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HtmlUtil;
import cn.hutool.json.JSONUtil;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author fishx
 * @version 1.0
 * @description: 定义请求包装类
 * @date 2023/8/23 17:18
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {
  public XssHttpServletRequestWrapper(HttpServletRequest request) {
    super(request);
  }

  @Override
  public String getParameter(String name) {
    String value = super.getParameter(name);
    if (!StrUtil.isEmpty(value)) {
      value = HtmlUtil.filter(value);
    }
    return value;
  }

  @Override
  public String[] getParameterValues(String name) {
    String[] values = super.getParameterValues(name);
    if (values != null) {
      for (int i = 0; i < values.length; i++) {
        String value = super.getParameter(name);
        if (!StrUtil.isEmpty(value)) {
          value = HtmlUtil.filter(value);
        }
        values[i] = value;
      }
    }
    return values;
  }

  @Override
  public Map<String, String[]> getParameterMap() {
    Map<String, String[]> parameterMap = super.getParameterMap();
    LinkedHashMap<String, String[]> hashMap = new LinkedHashMap<>();
    for (String key : parameterMap.keySet()) {
      String[] values = parameterMap.get(key);
      for (int i = 0; i < values.length; i++) {
        String value = super.getParameter(key);
        if (!StrUtil.isEmpty(value)) {
          value = HtmlUtil.filter(value);
        }
        values[i] = value;
      }
      hashMap.put(key, values);
    }
    return hashMap;
  }

  @Override
  public String getHeader(String name) {
    String header = super.getHeader(name);
    if (!StrUtil.isEmpty(header)) {
      header = HtmlUtil.filter(header);
    }
    return header;
  }

  @Override
  public ServletInputStream getInputStream() throws IOException {
    ServletInputStream input = super.getInputStream();
    InputStreamReader reader = new InputStreamReader(input, StandardCharsets.UTF_8);
    BufferedReader buffer = new BufferedReader(reader);
    StringBuilder body = new StringBuilder();
    String line = buffer.readLine();
    while (line != null) {
      body.append(line);
      line = buffer.readLine();
    }
    buffer.close();
    reader.close();
    input.close();
    Map<String, Object> map = JSONUtil.parseObj(body.toString());
    Map<String, Object> result = new LinkedHashMap<>();
    for (String key : map.keySet()) {
      Object val = map.get(key);
      if (val instanceof String) {
        if (!StrUtil.isEmpty(val.toString())) {
          result.put(key, HtmlUtil.filter(val.toString()));
        }
      } else {
        result.put(key, val);
      }
    }
    String json = JSONUtil.toJsonStr(result);
    ByteArrayInputStream bis = new ByteArrayInputStream(json.getBytes());
    return new ServletInputStream() {
      @Override
      public boolean isFinished() {
        return false;
      }

      @Override
      public boolean isReady() {
        return false;
      }

      @Override
      public void setReadListener(ReadListener readListener) {

      }

      @Override
      public int read() throws IOException {
        return bis.read();
      }
    };
  }
}
