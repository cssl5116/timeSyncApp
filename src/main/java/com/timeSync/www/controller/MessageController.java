package com.timeSync.www.controller;

import cn.hutool.core.util.StrUtil;
import com.timeSync.www.config.shiro.JwtUtils;
import com.timeSync.www.dto.RoleSearchForm;
import com.timeSync.www.dto.SearchMessageByPageForm;
import com.timeSync.www.exception.ConditionException;
import com.timeSync.www.service.MessageService;
import com.timeSync.www.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author fishx
 * @version 1.0
 * @description: 消息模块接口
 * @date 2023/9/4 09:51
 */
@RestController
@RequestMapping("/message")
@Api("消息模块接口")
public class MessageController {
  @Resource
  JwtUtils jwtUtils;
  @Resource
  MessageService messageService;


  @PostMapping("/searchMessageByPage")
  @ApiOperation("获取分页消息列表")
  public R searchMessageByPage(@Valid @RequestBody SearchMessageByPageForm form, @RequestHeader("token") String token) {
    int userId = jwtUtils.getUserId(token);
    Integer length = form.getLength();
    long start = (long) (form.getPage() - 1) * length;
    return R.ok().put("result", messageService.searchMessageByPage(userId, start, length));
  }

  @PostMapping("/searchMessageById")
  @ApiOperation("根据ID查询消息")
  public R searchMessageById(String id) {
    if (StrUtil.isBlank(id)) throw new ConditionException("id不能为空");
    return R.ok().put("result", messageService.searchMessageById(id));
  }

  @PostMapping("/updateUnreadMessage")
  @ApiOperation("未读消息更新成已读消息")
  public R updateUnreadMessage(String id) {
    if (StrUtil.isBlank(id)) throw new ConditionException("id不能为空");
    return R.ok().put("result", messageService.updateUnreadMessage(id) == 1);
  }

  @PostMapping("/deleteMessageRefById")
  @ApiOperation("删除消息")
  public R deleteMessageRefById(String id) {
    if (StrUtil.isBlank(id)) throw new ConditionException("id不能为空");
    return R.ok().put("result", messageService.deleteMessageRefById(id) == 1);
  }

  @GetMapping("/refreshMessage")
  @ApiOperation("刷新用户的消息")
  public R refreshMessage(@RequestHeader("token") String token) {
    int userId = jwtUtils.getUserId(token);
    return messageService.refreshMessage(userId);
  }

  @GetMapping("/list")
  @ApiOperation("列表查询")
  public R selectList(
      @RequestParam(defaultValue = "1") Integer offset,
      @RequestParam(defaultValue = "5") Integer size
  ) {
    long start = (long) (offset - 1) * size;
    return R.ok().put("data", messageService.searchList(start, size));
  }
}
