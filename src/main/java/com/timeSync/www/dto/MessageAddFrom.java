package com.timeSync.www.dto;

import lombok.Data;

import java.util.ArrayList;

/**
 * @author fishx
 * @version 1.0
 * @description: TODO
 * @date 2023/9/16 04:10
 */
@Data
public class MessageAddFrom {
  private Integer senderId;
  private String msg;
  private ArrayList<String> ref;
}
