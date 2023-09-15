package com.timeSync.www;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import com.timeSync.www.entity.MessageEntity;
import com.timeSync.www.entity.MessageRefEntity;
import com.timeSync.www.service.MenuService;
import com.timeSync.www.service.MessageService;
import com.timeSync.www.task.EmailTask;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
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
  @Resource
  private EmailTask emailTask;
  @Resource
  private JavaMailSender javaMailSender;

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
  void sendEmail(){
    SimpleMailMessage mailMessage = new SimpleMailMessage();
    //设置发送人
    mailMessage.setFrom("3120793223@qq.com");
    //邮件主题
    mailMessage.setSubject("新型冠状病毒防护指南");
    //邮件内容：普通文件无法解析html标签
    mailMessage.setText("<h1 style='color: red'>好好在家待着.....</h1>");
    //收件人
    mailMessage.setTo("3120793223@qq.com");
    //发送邮件
    javaMailSender.send(mailMessage);
  }

  @Test
  public void send2() throws MessagingException {
    //创建复杂邮件对象
    MimeMessage mimeMessage = javaMailSender.createMimeMessage();
    //发送复杂邮件的工具类
    //true - 能够添加附件
    MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true,"utf-8");
    helper.setFrom("3120793223@qq.com");
    helper.setSubject("欢迎新同学入职");
    //true - 能够再内容中编写html标签 - 会解析
    helper.setText(
        "<h1>你的邀请码为<u style='color: red'>"+ RandomUtil.randomNumbers(6) +"</u>，你现在可以进入时刻在线协同办公系统</h1>",
        true);
    //收件人
    helper.setTo("15073058367@163.com");
    javaMailSender.send(mimeMessage);
  }
}
