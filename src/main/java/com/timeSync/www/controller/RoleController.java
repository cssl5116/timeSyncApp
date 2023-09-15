package com.timeSync.www.controller;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.timeSync.www.dto.InsertRoleFrom;
import com.timeSync.www.dto.RoleSearchForm;
import com.timeSync.www.entity.TbRole;
import com.timeSync.www.service.RoleService;
import com.timeSync.www.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Date;

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

  @PostMapping("/save")
  @ApiOperation("角色添加")
  public R save(@Valid @RequestBody InsertRoleFrom insertRoleFrom){
    TbRole tbRole = new TbRole();
    tbRole.setRoleName(insertRoleFrom.getRoleName());
    tbRole.setPermissions(JSONUtil.toJsonStr(insertRoleFrom.getMenuList()));
    tbRole.setUpdateAt(new Date());
    roleService.save(tbRole);
    return R.ok("添加成功");
  }

  @PatchMapping("/update")
  @ApiOperation("角色修改")
  public R update(@Valid @RequestBody InsertRoleFrom insertRoleFrom){
    TbRole tbRole = new TbRole();
    tbRole.setRoleName(insertRoleFrom.getRoleName());
    tbRole.setPermissions(JSONUtil.toJsonStr(insertRoleFrom.getMenuList()));
    tbRole.setId(insertRoleFrom.getId());
    roleService.update(tbRole);
    return R.ok("修改成功");
  }

  @DeleteMapping("/delete/{id}")
  @ApiOperation("删除角色")
  public R delete(@PathVariable("id") Integer id){
    roleService.exists(id);
    return R.ok("删除成功");
  }

  @GetMapping("/roleList")
  @ApiOperation("查询角色")
  public R selectRole() {
    return R.ok().put("list", roleService.selectRole());
  }
}
