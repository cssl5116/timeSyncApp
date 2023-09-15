package com.timeSync.www.controller;

import com.timeSync.www.config.shiro.JwtUtils;
import com.timeSync.www.dto.RoleSearchForm;
import com.timeSync.www.service.MenuService;
import com.timeSync.www.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

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

  @GetMapping("/menus")
  @ApiOperation("查询所有权限")
  public R menusList() {
    return R.ok().put("data", tbMenuService.menus());
  }
}
