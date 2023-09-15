package com.timeSync.www.controller;

import com.timeSync.www.dto.RoleSearchForm;
import com.timeSync.www.service.RoleService;
import com.timeSync.www.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author fishx
 * @version 1.0
 * @date 2023/9/13 17:13
 */
@RestController
@RequestMapping("/role")
@Api("角色模块web接口")
public class RoleController {
  @Resource
  RoleService roleService;

  @GetMapping("/list")
  @ApiOperation("列表查询")
  public R selectList(@Valid RoleSearchForm form) {
    return R.ok().put("data", roleService.roleList(form));
  }

  @GetMapping("/roleList")
  @ApiOperation("查询角色")
  public R selectRole() {
    return R.ok().put("list", roleService.selectRole());
  }
}
