package com.timeSync.www.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * @author fishx
 * @version 1.0
 * @description: 消息模块数据模型
 * @date 2023/9/3 12:00
 */
@Data
@Document(collation = "message")
public class MessageEntity implements Serializable {
  @Id
  private String _id;
  @Indexed(unique = true)
  private String uuid;
  @Indexed
  private Integer senderId;
  private String senderPhoto = "message.png";
  private String senderName;
  private String msg;
  @Indexed
  private Date sendTime;
}
