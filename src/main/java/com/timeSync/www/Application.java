package com.timeSync.www;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author fishx
 * @version 1.0
 * @description: 项目启动类
 * @date 2023/8/23 11:52
 */
@SpringBootApplication
@ServletComponentScan
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
