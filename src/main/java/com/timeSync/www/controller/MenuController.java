package com.timeSync.www.controller;

import com.timeSync.www.config.shiro.JwtUtils;
import com.timeSync.www.service.MenuService;
import com.timeSync.www.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Api("菜单接口")
@RequestMapping("/menu")
public class MenuController {
  @Resource
  private MenuService tbMenuService;
  @Resource
  private JwtUtils jwtUtils;

  @PostMapping("/list")
  @ApiOperation("查询菜单列表")
  public R selectMenu(@RequestHeader("token") String token) {
    int userId = jwtUtils.getUserId(token);
    return R.ok().put("list", tbMenuService.findMenuByTypeId(userId));
  }
}
