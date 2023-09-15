package com.timeSync.www.task;

import cn.hutool.core.util.RandomUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.Serializable;

@Component
@Scope("prototype")
public class EmailTask implements Serializable {
  @Resource
  private JavaMailSender javaMailSender;
  @Value("${time-sync.email.system}")
  private String mailbox;

  @Async
  public void sendAsync(String email,String code) throws MessagingException {
    //创建复杂邮件对象
    MimeMessage mimeMessage = javaMailSender.createMimeMessage();
    //发送复杂邮件的工具类
    //true - 能够添加附件
    MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "utf-8");
    helper.setSubject("欢迎新同学入职");
    //true - 能够再内容中编写html标签 - 会解析
    helper.setText(
        "<h1>你的邀请码为<u style='color: red'>" + code + "</u>，你现在可以进入时刻在线协同办公系统</h1>",
        true);
    //收件人
    helper.setTo(email);
    helper.setFrom(mailbox);
    javaMailSender.send(mimeMessage);
  }
}
