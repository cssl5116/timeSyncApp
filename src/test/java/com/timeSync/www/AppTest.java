package com.timeSync.www;

import cn.hutool.core.util.RandomUtil;
import com.timeSync.www.service.MenuService;
import com.timeSync.www.utils.WebUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class AppTest {
  @Resource
  private MenuService tbMenuService;
  @Test
  public void test1(){
    tbMenuService.findMenuByTypeId().forEach(System.out::println);
  }

  @Test
  public void test2(){
    try {
      System.out.println(WebUtils.sms("18569521613"));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
