package com.timeSync.www.controller;

import com.timeSync.www.config.shiro.JwtUtils;
import com.timeSync.www.controller.form.LoginForm;
import com.timeSync.www.controller.form.RegisterForm;
import com.timeSync.www.service.UserService;
import com.timeSync.www.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
@Api("用户模块web接口")
public class UserController {
  @Resource
  private UserService userService;
  @Resource
  private JwtUtils jwtUtils;
  @Resource
  private StringRedisTemplate stringRedisTemplate;
  @Value("${time-sync.jwt.cache-expire}")
  private int cacheExpire;

  @PostMapping("/register")
  @ApiOperation("注册用户")
  public R register(@Valid @RequestBody RegisterForm form) {
    int id = userService.registerUser(form.getRegisterCode(), form.getCode(), form.getNickName(), form.getPhoto());
    String token = jwtUtils.createToken(id);
    Set<String> permsSet = userService.searchUserPermissions(id);
    saveCacheToken(token, id);
    return R.ok("用户注册成功").put("token", token).put("permission", permsSet);
  }

  @PostMapping("/login")
  @ApiOperation("登录系统")
  public  R login(@Valid @RequestBody LoginForm form){
    int id = userService.login(form.getCode());
    String token=jwtUtils.createToken(id);
    saveCacheToken(token,id);
    Set<String> permsSet = userService.searchUserPermissions(id);
    return R.ok("登录成功").put("token",token).put("permission",permsSet);
  }
  @RequestMapping("/upload/img")
  public R upload(MultipartFile file, HttpServletRequest request) {
    return userService.upload(file, request);
  }

  private void saveCacheToken(String token, int userId) {
    stringRedisTemplate.opsForValue().set(token, String.valueOf(userId), cacheExpire, TimeUnit.DAYS);
  }


}
