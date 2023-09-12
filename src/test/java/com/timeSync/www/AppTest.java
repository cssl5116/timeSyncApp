package com.timeSync.www;

import com.timeSync.www.service.MenuService;
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

  }
}
