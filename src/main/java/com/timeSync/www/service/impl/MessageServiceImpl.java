package com.timeSync.www.service.impl;

import com.timeSync.www.entity.MessageEntity;
import com.timeSync.www.entity.MessageRefEntity;
import com.timeSync.www.mapper.MessageDao;
import com.timeSync.www.mapper.MessageRefDao;
import com.timeSync.www.service.MessageService;
import com.timeSync.www.task.MessageTask;
import com.timeSync.www.utils.R;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author fishx
 * @version 1.0
 * @description: TODO
 * @date 2023/9/3 14:14
 */
@Service
public class MessageServiceImpl implements MessageService {
  @Resource
  MessageRefDao messageRefDao;
  @Resource
  MessageDao messageDao;
  @Resource
  MessageTask messageTask;

  @Override
  public String insertMessage(MessageEntity message) {
    return messageDao.insert(message);
  }

  @Override
  public String insertMessageRef(MessageRefEntity messageRef) {
    return messageRefDao.insert(messageRef);
  }

  @Override
  public List<HashMap> searchMessageByPage(int userId, long start, int length) {
    return messageDao.searchMessageByPage(userId, start, length);
  }

  @Override
  public long searchUnreadCount(Integer userId) {
    return messageRefDao.searchUnreadCount(userId);
  }

  @Override
  public long searchLastCount(Integer userId) {
    return messageRefDao.searchLastCount(userId);
  }

  @Override
  public HashMap searchMessageById(String messageId) {
    return messageDao.searchMessageById(messageId);
  }

  @Override
  public long updateUnreadMessage(String messageRefId) {
    return messageRefDao.updateUnreadMessage(messageRefId);
  }

  @Override
  public long deleteMessageRefById(String messageRefId) {
    return messageRefDao.deleteMessageRefById(messageRefId);
  }

  @Override
  public long deleteUserMessageRef(String userId) {
    return messageRefDao.deleteUserMessageRef(userId);
  }

  @Override
  public R refreshMessage(int userId) {
    // 异步接收消息
    messageTask.receiveAysnc(String.valueOf(userId));
    // 查询接收了多少条消息
    long lastRows = searchLastCount(userId);
    // 查询未读数据
    long unreadRows = searchUnreadCount(userId);
    return R.ok().put("lastRows", lastRows).put("unreadRows", unreadRows);
  }

  @Override
  public Map<String, Object> searchList(long start, Integer size) {
    HashMap<String, Object> map = new HashMap<>();
    map.put("list", messageDao.searchList(start, size));
    map.put("total", messageDao.totalCount());
    return map;
  }
}
