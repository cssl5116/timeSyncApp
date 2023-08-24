package com.timeSync.www.config;

import com.timeSync.www.config.shiro.OAuth2Filter;
import com.timeSync.www.config.shiro.OAuth2Realm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author fishx
 * @version 1.0
 * @description: 把 OAuth2Filter 和 OAuth2Realm 配置到Shiro框架
 * @date 2023/8/24 09:18
 */
@Configuration
public class ShiroConfig {

  @Bean
  public SecurityManager securityManager(OAuth2Realm realm) {
    DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
    securityManager.setRealm(realm);
    securityManager.setRememberMeManager(null);
    return securityManager;
  }

  @Bean
  public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager manager, OAuth2Filter filter) {
    ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
    factoryBean.setSecurityManager(manager);
    // oauth过滤
    Map<String, Filter> map = new HashMap<>();
    map.put("oauth2", filter);
    factoryBean.setFilters(map);

    Map<String, String> filterMap = new LinkedHashMap<>();
    filterMap.put("/webjars/**", "anon");
    filterMap.put("/druid/**", "anon");
    filterMap.put("/app/**", "anon");
    filterMap.put("/sys/login", "anon");
    filterMap.put("/swagger/**", "anon");
    filterMap.put("/v2/api-docs", "anon");
    filterMap.put("/swagger-ui.html", "anon");
    filterMap.put("/swagger-resources/**", "anon");
    filterMap.put("/captcha.jpg", "anon");
    filterMap.put("/user/register", "anon");
    filterMap.put("/user/login", "anon");
    filterMap.put("/test/**", "anon");
    filterMap.put("/**", "oauth2");
    factoryBean.setFilterChainDefinitionMap(filterMap);

    return factoryBean;
  }

  @Bean
  public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
    return new LifecycleBeanPostProcessor();
  }

  @Bean
  public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager manager){
    AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
    advisor.setSecurityManager(manager);
    return advisor;
  }
}
