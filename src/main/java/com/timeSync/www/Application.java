package com.timeSync.www;

import cn.hutool.core.util.StrUtil;
import com.timeSync.www.entity.SysConfig;
import com.timeSync.www.entity.SystemConstants;
import com.timeSync.www.mapper.SysConfigMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.List;

/**
 * @author fishx
 * @version 1.0
 * @description: 项目启动类
 * @date 2023/8/23 11:52
 */
@EnableAsync
@SpringBootApplication
@ServletComponentScan
@Slf4j
public class Application {
  @Resource
  private SysConfigMapper sysConfigMapper;
  @Resource
  private SystemConstants constants;
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @PostConstruct
  public void init() {
    List<SysConfig> list = sysConfigMapper.selectAllParam();
    list.forEach(item -> {
      String key = item.getParamKey();
      key = StrUtil.toCamelCase(key);
      String value = item.getParamValue();
      try {
        Field field = constants.getClass().getDeclaredField(key);
        field.set(constants, value);
      } catch (NoSuchFieldException | IllegalAccessException e) {
        log.error("执行异常", e);
      }
    });
  }
}
