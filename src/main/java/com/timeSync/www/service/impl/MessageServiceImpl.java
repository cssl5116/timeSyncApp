package com.timeSync.www.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.timeSync.www.dto.MessageAddFrom;
import com.timeSync.www.entity.MessageEntity;
import com.timeSync.www.entity.MessageRefEntity;
import com.timeSync.www.entity.TbUser;
import com.timeSync.www.mapper.MessageDao;
import com.timeSync.www.mapper.MessageRefDao;
import com.timeSync.www.mapper.TbUserMapper;
import com.timeSync.www.service.MessageService;
import com.timeSync.www.service.UserService;
import com.timeSync.www.task.MessageTask;
import com.timeSync.www.utils.R;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

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
  @Resource
  TbUserMapper userMapper;

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

  @Override
  public R addMessage(MessageAddFrom from) {
    MessageEntity entity = new MessageEntity();
    if (from.getSenderId() == 0) {
      entity.setSenderId(0);
      entity.setSenderName("系统消息");
    } else {
      TbUser tbUser = userMapper.searchById(from.getSenderId());
      entity.setSenderId(tbUser.getId());
      entity.setSenderName(tbUser.getNickname());
    }
    entity.setMsg(from.getMsg());
    entity.setUuid(IdUtil.simpleUUID());
    entity.setSendTime(new Date());
    String id = this.insertMessage(entity);
    MessageRefEntity ref = new MessageRefEntity();
    ref.setMessageId(id);
    for (String s : from.getRef()) {
      ref.setReceiverId(userMapper.findByNickName(s));
    }
    ref.setLastFlag(true);
    ref.setReadFlag(false);
    this.insertMessageRef(ref);
    return R.ok();
  }

  @Override
  public R remove(String id) {
    return messageDao.remove(id) > 0 ? R.ok("删除成功") : R.error("删除失败");
  }
}
