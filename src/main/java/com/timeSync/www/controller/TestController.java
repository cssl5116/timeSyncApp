package com.timeSync.www.controller;

import com.timeSync.www.dto.TestSayHelloForm;
import com.timeSync.www.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author fishx
 * @version 1.0
 * @description: 测试Swagger
 * @date 2023/8/23 15:52
 */
@RestController
@RequestMapping("/test")
@Api("测试Swagger,测试Web接口")
public class TestController {
  @GetMapping("/sayHello")
  @ApiOperation("简单测试一下")
  public R sayHello() {
    return R.ok().put("message", "Hello,World!");
  }

  @PostMapping("/sayHi")
  @ApiOperation("数据校验,测试一下")
  public R sayHello(@Valid @RequestBody TestSayHelloForm form) {
    return R.ok().put("message",
        "Hi! " + form.getName() + ", 还存在注入攻击吗? " + form.getOther());
  }
}
