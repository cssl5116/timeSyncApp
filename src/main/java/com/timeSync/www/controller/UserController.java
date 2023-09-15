package com.timeSync.www.controller;

import cn.hutool.json.JSONUtil;
import com.alibaba.druid.support.json.JSONUtils;
import com.timeSync.www.config.shiro.JwtUtils;
import com.timeSync.www.dto.*;
import com.timeSync.www.entity.TbUser;
import com.timeSync.www.exception.ConditionException;
import com.timeSync.www.mapper.TbRoleMapper;
import com.timeSync.www.service.UserService;
import com.timeSync.www.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

  @Resource
  private TbRoleMapper roleMapper;

  @PostMapping("/register")
  @ApiOperation("注册用户")
  public R register(@Valid @RequestBody RegisterForm form) {
    int id = userService.registerUser(form.getRegisterCode(), form.getCode(), form.getNickName(), form.getPhoto());
    if (id==0){
      return R.error("注册码无效");
    }
    String token = jwtUtils.createToken(id);
    Set<String> permsSet = userService.searchUserPermissions(id);
    saveCacheToken(token, id);
    return R.ok("用户注册成功").put("token", token).put("permission", permsSet);
  }

  @PostMapping("/save")
  @ApiOperation("创建用户")
  public R register(@RequestBody TbUser user) {
    ArrayList<Object> role = (ArrayList<Object>) user.getRole();
    ArrayList<Integer> ids = new ArrayList<>();
    for (Object o : role) {
      int id = roleMapper.findByName(o.toString());
      ids.add(id);
    }
    user.setRole(JSONUtils.toJSONString(ids));

    if (userService.insertUser(user)) {
      return R.ok("新增成功");
    }
    return R.error("新增失败");
  }
  @PatchMapping("/update")
  @ApiOperation("修改用户")
  public R update(@RequestBody TbUser user) {
    ArrayList<Object> role = (ArrayList<Object>) user.getRole();
    ArrayList<Integer> ids = new ArrayList<>();
    for (Object o : role) {
      int id = roleMapper.findByName(o.toString());
      ids.add(id);
    }
    user.setRole(JSONUtils.toJSONString(ids));
    if (userService.update(user)) {
      return R.ok("修改成功");
    }
    return R.error("修改失败");
  }

  @PostMapping("/login")
  @ApiOperation("登录系统")
  public R login(@Valid @RequestBody LoginForm form) {
    int id = userService.login(form.getCode());
    String token = jwtUtils.createToken(id);
    saveCacheToken(token, id);
    Set<String> permsSet = userService.searchUserPermissions(id);
    return R.ok("登录成功").put("token", token).put("permission", permsSet);
  }

  @PostMapping("/check")
  @ApiOperation("获取验证码")
  public R check(String phone) {
    return userService.check(phone);
  }

  @RequestMapping("/upload/img")
  public R upload(MultipartFile file, HttpServletRequest request) {
    return userService.upload(file, request);
  }

  private void saveCacheToken(String token, int userId) {
    stringRedisTemplate.opsForValue().set(token, String.valueOf(userId), cacheExpire, TimeUnit.DAYS);
  }

  @PostMapping("/loginH")
  @ApiOperation("手机号登录")
  public R loginH(String phone, String code) {
    TbUser tbUser = userService.loginH(phone, code);
    if (tbUser == null) throw new ConditionException("登录失败");
    Integer id = tbUser.getId();
    String token = jwtUtils.createToken(id);
    saveCacheToken(token, id);
    Set<String> permsSet = userService.searchUserPermissions(id);
    return R.ok("登录成功").put("user", tbUser).put("token", token).put("permission", permsSet);
  }

  @GetMapping("/list")
  @ApiOperation("查询用户")
  public R selectUser(UserSeacherForm form) {
    return R.ok().put("data", userService.userList(form));
  }

  @GetMapping("/all")
  public R selectAllUser() {
    return R.ok().put("data", userService.selectAllUser());
  }

  @PostMapping("/searchUserGroupByDept")
  @ApiOperation("查询员工列表，按照部门分组排列")
  @RequiresPermissions(value = {"ROOT", "EMPLOYEE:SELECT"}, logical = Logical.OR)
  public R searchUserGroupByDept(@Valid @RequestBody SearchUserGroupByDeptForm form) {
    ArrayList<HashMap> list = userService.searchUserGroupByDept(form.getKeyword());
    return R.ok().put("result", list);
  }

  @PostMapping("/searchMembers")
  @ApiOperation("查询成员")
  @RequiresPermissions(value = {"ROOT", "MEETING:INSERT", "MEETING:UPDATE"}, logical = Logical.OR)
  public R searchMembers(@Valid @RequestBody SearchMembersForm form) {
    if (!JSONUtil.isJsonArray(form.getMembers())) {
      throw new ConditionException("members不是JSON数组");
    }
    List param = JSONUtil.parseArray(form.getMembers()).toList(Integer.class);
    ArrayList list = userService.searchMembers(param);
    return R.ok().put("result", list);
  }
  @DeleteMapping("/delete/{id}")
  @ApiOperation("删除成员")
  public R delete( @PathVariable Integer id){
    return userService.delete(id)? R.ok("删除成功"):R.error("删除失败");
  }

}
