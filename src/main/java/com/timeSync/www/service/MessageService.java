package com.timeSync.www.service;

import com.timeSync.www.dto.MessageAddFrom;
import com.timeSync.www.entity.MessageEntity;
import com.timeSync.www.entity.MessageRefEntity;
import com.timeSync.www.utils.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface MessageService {
  String insertMessage(MessageEntity message);

  String insertMessageRef(MessageRefEntity messageRef);

  List<HashMap> searchMessageByPage(int userId, long start, int length);

  long searchUnreadCount(Integer userId);

  long searchLastCount(Integer userId);

  HashMap searchMessageById(String messageId);

  long updateUnreadMessage(String messageRefId);

  long deleteMessageRefById(String messageRefId);

  long deleteUserMessageRef(String userId);

  R refreshMessage(int userId);

  Map<String, Object> searchList(long start, Integer size);

  R addMessage(MessageAddFrom from);

  R remove(String id);
}
