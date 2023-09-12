package com.timeSync.www;

import cn.hutool.core.util.IdUtil;
import com.timeSync.www.entity.MessageEntity;
import com.timeSync.www.entity.MessageRefEntity;
import com.timeSync.www.service.MenuService;
import com.timeSync.www.service.MessageService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 先移除websocket配置类注解,再生成测试数据
 */
@SpringBootTest
public class ApplicationTests {
  @Resource
  private MessageService messageService;
  @Resource
  private MenuService menuService;

  @Test
  void contextLoads() {
    for (int i = 0; i <= 20; i++) {
      MessageEntity entity = new MessageEntity();
      entity.setUuid(IdUtil.simpleUUID());
      entity.setSenderId(0);
      entity.setSenderName("系统消息");
      entity.setMsg("这是第" + i + "条测试消息");
      entity.setSendTime(new Date());
      String id = messageService.insertMessage(entity);
      MessageRefEntity ref = new MessageRefEntity();
      ref.setMessageId(id);
      ref.setReceiverId(18);
      ref.setLastFlag(true);
      ref.setReadFlag(false);
      messageService.insertMessageRef(ref);
    }
  }

  @Test
  void menuTest() {
    menuService.findMenuByTypeId().forEach(System.out::println);
  }
}
