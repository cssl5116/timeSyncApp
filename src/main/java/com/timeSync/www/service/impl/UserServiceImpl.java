package com.timeSync.www.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.timeSync.www.config.shiro.JwtUtils;
import com.timeSync.www.dto.UserSeacherForm;
import com.timeSync.www.entity.MessageEntity;
import com.timeSync.www.entity.TbUser;
import com.timeSync.www.exception.ConditionException;
import com.timeSync.www.mapper.TbDeptMapper;
import com.timeSync.www.mapper.TbUserMapper;
import com.timeSync.www.service.UserService;
import com.timeSync.www.task.EmailTask;
import com.timeSync.www.task.MessageTask;
import com.timeSync.www.utils.OosUtils;
import com.timeSync.www.utils.R;
import com.timeSync.www.utils.WebUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
@Scope("prototype")
public class UserServiceImpl implements UserService {
  @Value("${wx.app-id}")
  private String appId;
  @Value("${wx.app-secret}")
  private String appSecret;
  @Resource
  private TbUserMapper tbUserMapper;
  @Resource
  private JwtUtils jwtUtils;
  @Resource
  private MessageTask messageTask;
  @Resource
  private StringRedisTemplate stringRedisTemplate;
  @Resource
  private TbDeptMapper deptDao;
  @Resource
  private EmailTask emailTask;

  private String getOpenId(String code) {
    String url = "https://api.weixin.qq.com/sns/jscode2session";
    HashMap<String, Object> map = new HashMap<>();
    map.put("appid", appId);
    map.put("secret", appSecret);
    map.put("js_code", code);
    map.put("grant_type", "authorization_code");
    String response = HttpUtil.post(url, map);
    JSONObject json = JSONUtil.parseObj(response);
    String openId = json.getStr("openid");
    if (openId == null || openId.length() == 0) {
      throw new RuntimeException("临时登录凭证错误");
    }
    return openId;
  }

  @Override
  public int registerUser(String registerCode, String code, String nickname, String photo) {
    if ("000000".equals(registerCode)) {
      boolean bool = tbUserMapper.havaRootUser();
      if (!bool) {
        String openId = getOpenId(code);
        HashMap<String, Object> param = new HashMap<>();
        param.put("openId", openId);
        param.put("nickname", nickname);
        param.put("photo", photo);
        param.put("role", "[0]");
        param.put("tel", "18569521613");
        param.put("status", 1);
        param.put("createTime", new Date());
        param.put("root", true);
        if (tbUserMapper.insert(param) < 0) throw new ConditionException("注册失败");
        MessageEntity entity = new MessageEntity();
        entity.setSenderId(0);
        entity.setSenderName("系统消息");
        entity.setUuid(IdUtil.simpleUUID());
        entity.setMsg("欢迎您注册成为超级管理员,请及时更新你的员工个人信息.");
        entity.setSendTime(DateUtil.date());
        Integer id = tbUserMapper.searchIdByOpenId(openId);
        messageTask.sendAsync(String.valueOf(id), entity);
        return id;
      } else {
        throw new ConditionException("无法绑定超级管理员账号");
      }
    } else {
      TbUser tbUser = tbUserMapper.havaCode(registerCode);
      if (tbUser!=null) {
        String openId = getOpenId(code);
        if (tbUserMapper.updateQ(openId, nickname, photo, registerCode) < 0) throw new ConditionException("注册失败");
        MessageEntity entity = new MessageEntity();
        entity.setSenderId(0);
        entity.setSenderName("系统消息");
        entity.setUuid(IdUtil.simpleUUID());
        entity.setMsg("欢迎您加入,请及时更新你的员工个人信息.");
        entity.setSendTime(DateUtil.date());
        Integer id = tbUserMapper.searchIdByOpenId(openId);
        messageTask.sendAsync(String.valueOf(id), entity);
        return id;
      }
    }
    return 0;
  }

  @Override
  public Set<String> searchUserPermissions(int userId) {
    Set<String> permissions = tbUserMapper.searchUserPermissions(userId);
    return permissions;
  }

  @Override
  public TbUser searchById(int userId) {
    return tbUserMapper.searchById(userId);
  }

  @Override
  public Integer login(String code) {
    String openId = getOpenId(code);
    Integer id = tbUserMapper.searchIdByOpenId(openId);
    if (id == null) {
      throw new ConditionException("用户不存在");
    }
    // 从消息队列中接受信息,转移到消息表
    messageTask.receiveAysnc(String.valueOf(id));
    return id;
  }

  @Transactional
  @Override
  public R upload(MultipartFile file, HttpServletRequest request) {
    String realPath = System.getProperty("user.dir") + "/target/img/";
    try {
      // 暂存文件
      String fileName = WebUtils.uploadFile(file, realPath);
      if (StrUtil.isBlank(fileName)) {
        throw new ConditionException("图片上传失败");
      }
      // oos上传
      String absPath = realPath + fileName;
      fileName = "user/" + fileName;
      OosUtils.upload(absPath, fileName);
      // 删除临时文件
      File tempfile = new File(absPath);
      if (tempfile.isFile()) {
        if (tempfile.delete()) {
          System.out.println("删除临时文件成功");
        }
      }
      // 更新数据库
      int userId = jwtUtils.getUserId(request.getHeader("token"));
      if (tbUserMapper.updateImg(userId, fileName) < 0) {
        throw new ConditionException("图片上传失败");
      }
      return R.ok();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public TbUser loginH(String phone, String code) {
    String cacheCode = stringRedisTemplate.opsForValue().get("user:phone:" + phone);
    TbUser tbUser = tbUserMapper.loginH(phone);
    if (code.equals(cacheCode)) {
      return tbUser;
    }
    return null;
  }

  @Override
  public R check(String phone) {
    if (tbUserMapper.check(phone)) {
      try {
//        String sms = WebUtils.sms(phone);
        String sms = RandomUtil.randomNumbers(6);
        stringRedisTemplate.opsForValue()
            .set("user:phone:" + phone, sms, 5, TimeUnit.MINUTES);
        return R.ok("发送成功");
      } catch (Exception e) {
        System.out.println(e.getMessage() + ", " + e.getCause());
        return R.error("验证码发送失败");
      }
    }
    return R.error("该用户不存在");
  }

  @Override
  public ArrayList<HashMap> searchUserGroupByDept(String keyword) {
    ArrayList<HashMap> list_1 = deptDao.searchDeptMembers(keyword);
    ArrayList<HashMap> list_2 = tbUserMapper.searchUserGroupByDept(keyword);
    //进行合并
    for (HashMap map_1 : list_1) {
      long deptId = (Long) map_1.get("id");
      ArrayList members = new ArrayList();
      for (HashMap map_2 : list_2) {
        long id = (Long) map_2.get("deptId");
        if (deptId == id) {
          members.add(map_2);
        }
      }
      map_1.put("members", members);
    }
    return list_1;
  }

  @Override
  public ArrayList<HashMap> searchMembers(List param) {
    ArrayList<HashMap> list = tbUserMapper.searchMembers(param);
    return list;
  }


  @Override
  public PageInfo<TbUser> userList(UserSeacherForm form) {
    PageHelper.startPage(form.getOffset(),form.getSize());
    return new PageInfo<>(tbUserMapper.selectUser(form));
  }

  @Override
  public boolean insertUser(TbUser user) {
    user.setCode(RandomUtil.randomNumbers(6));
    user.setCreateTime(new Date());
    boolean b = tbUserMapper.insertUser(user) > 0;
    if (b) {
      try {
        emailTask.sendAsync(user.getEmail(), user.getCode());
      } catch (MessagingException e) {
        throw new RuntimeException(e);
      }
    }
    return b;
  }



  @Override
  public List<TbUser> selectAllUser() {
    return tbUserMapper.selectAllUser();
  }

  @Override
  public boolean update(TbUser user) {
    return tbUserMapper.update(user)>0;
  }

  @Override
  public boolean delete(int id) {
    return tbUserMapper.delete(id)>0;
  }
}
