package com.timeSync.www.controller;

import com.timeSync.www.service.TbMenuService;
import com.timeSync.www.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Api("菜单接口")
@RequestMapping("/menu")
public class MenuController {
  @Resource
  private TbMenuService tbMenuService;
  @PostMapping("/list")
  @ApiOperation("查询菜单列表")
  public R selectMenu() {
    return R.ok().put("list", tbMenuService.findMenuByTypeId());
  }
}
