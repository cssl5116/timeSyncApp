package com.timeSync.www.controller;

import com.timeSync.www.service.DeptService;
import com.timeSync.www.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/dept")
@Api("部门模块")
public class DeptController {
  @Resource
  private DeptService deptService;
  @ApiOperation("查询部门")
  @GetMapping("/deptList")
  public R selectDept(){
    return R.ok().put("list",deptService.selectDept());
  }
}
