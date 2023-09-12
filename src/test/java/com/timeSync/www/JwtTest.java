package com.timeSync.www;

import cn.hutool.core.date.DateUtil;
import com.timeSync.www.config.shiro.JwtUtils;
import com.timeSync.www.mapper.TbHolidaysMapper;
import com.timeSync.www.mapper.TbWorkdayMapper;
import com.timeSync.www.utils.OosUtils;
import com.timeSync.www.utils.WebUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author fishx
 * @version 1.0
 * @description: TODO
 * @date 2023/8/29 22:58
 */
@SpringBootTest
public class JwtTest {
  @Resource
  private JwtUtils jwtUtils;
  @Resource
  private TbHolidaysMapper holidaysMapper;
  @Resource
  private TbWorkdayMapper workdayMapper;

  @Test
  public void getId() {
    int userId = jwtUtils.getUserId("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2OTM3NTM5MTMsInVzZXJJZCI6OX0.qCLNbZ42uTH6daMq1MTujGN5u_EPJrdsWNF7WovadBo");
    System.out.println(userId);
  }

  @Test
  public void verifierToken() {
    try {
      jwtUtils.verifierToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2OTM3NTM5MTMsInVzZXJJZCI6OX0.qCLNbZ42uTH6daMq1MTujGN5u_EPJrdsWNF7WovadBo");
      System.out.println("验证无误");
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  @Test
  public void uploadStaticImg() {
    String realPath = System.getProperty("user.dir") + "/target/static";
    File file = new File(realPath);
    if (file.isDirectory()) {
      File[] files = file.listFiles();
      if (files != null) {
        for (File pic : files) {
          System.out.println("- - - - - - - - - - - - -");
          System.out.println(pic.getAbsolutePath() + ", " + pic.getName());
          try {
            OosUtils.upload(pic.getAbsolutePath(), pic.getName());
            System.out.println("上传成功");
            // 删除文件
            if (pic.isFile() && pic.delete()) {
              System.out.println("删除文件成功");
            }
          } catch (IOException e) {
            System.out.println("上传失败, 错误:" + e.getMessage());
          }
        }
      }
    }
  }

  @Test
  public void computeWorkingDay() {
    Map<String, Set<String>> map = WebUtils.computeWorkingDay();
    if (holidaysMapper.saveBatchHoliday(map.get("holiday_list"))) {
      System.out.println("holiday_list ok...");
    }
    if (workdayMapper.saveBatchWorkday(map.get("special_workday_list"))) {
      System.out.println("special_workday_list ok...");
    }
  }
}
