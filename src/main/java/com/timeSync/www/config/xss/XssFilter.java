package com.timeSync.www.config.xss;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author fishx
 * @version 1.0
 * @description: 预防XSS注入攻击--过滤器
 * @date 2023/8/23 17:50
 */
@WebFilter(urlPatterns = "/*")
public class XssFilter implements Filter {

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    Filter.super.init(filterConfig);
  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    XssHttpServletRequestWrapper wrapper = new XssHttpServletRequestWrapper(request);
    filterChain.doFilter(wrapper, servletResponse);
  }

  @Override
  public void destroy() {
    Filter.super.destroy();
  }
}
