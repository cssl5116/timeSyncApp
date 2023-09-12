package com.timeSync.www.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.timeSync.www.config.shiro.JwtUtils;
import com.timeSync.www.entity.TbUser;
import com.timeSync.www.exception.ConditionException;
import com.timeSync.www.mapper.TbUserMapper;
import com.timeSync.www.service.UserService;
import com.timeSync.www.utils.OosUtils;
import com.timeSync.www.utils.R;
import com.timeSync.www.utils.WebUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

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

  private String getOpenId(String code) {
    String url = "https://api.weixin.qq.com/sns/jscode2session";
    HashMap<String,Object> map = new HashMap<>();
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
        HashMap param = new HashMap();
        param.put("openId", openId);
        param.put("nickname", nickname);
        param.put("photo", photo);
        param.put("role", "[0]");
        param.put("tel","18569521613");
        param.put("status", 1);
        param.put("createTime", new Date());
        param.put("root", true);
        tbUserMapper.insert(param);
        int id = tbUserMapper.searchIdByOpenId(openId);
        return id;
      } else {
        throw new ConditionException("无法绑定超级管理员账号");
      }
    } else {

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
    String openId=getOpenId(code);
    Integer id = tbUserMapper.searchIdByOpenId(openId);
    if (id==null){
      throw new ConditionException("用户不存在");
    }
    //TODO 从消息队列中接收数据，转移到消息表
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
      fileName="user/"+fileName;
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
      if (tbUserMapper.updateImg(userId,fileName)<0) {
        throw new ConditionException("图片上传失败");
      }
      return R.ok();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
