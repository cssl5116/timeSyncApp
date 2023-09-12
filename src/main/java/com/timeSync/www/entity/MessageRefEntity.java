package com.timeSync.www.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author fishx
 * @version 1.0
 * @description: message_ref 记录接收人和已读状态
 * @date 2023/9/3 12:12
 */
@Data
@Document(collection = "message_ref")
public class MessageRefEntity {
  @Id
  private String _id;
  @Indexed
  private String messageId;
  @Indexed
  private Integer receiverId;
  @Indexed
  private Boolean readFlag;
  @Indexed
  private Boolean lastFlag;
}
