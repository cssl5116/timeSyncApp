package com.timeSync.www.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author fishx
 * @version 1.0
 * @description: 常量封装类
 * @date 2023/8/30 16:59
 */
@Data
@Component
public class SystemConstants {
  public String attendanceStartTime;
  public String attendanceTime;
  public String attendanceEndTime;
  public String closingStartTime;
  public String closingTime;
  public String closingEndTime;
}
