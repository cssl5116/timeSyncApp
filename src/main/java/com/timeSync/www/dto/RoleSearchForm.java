package com.timeSync.www.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author fishx
 * @version 1.0
 * @description: 后台角色管理-搜索表单
 * @date 2023/9/14 16:08
 */
@Data
public class RoleSearchForm {
  @NotNull
  @Min(1)
  private int offset;
  @NotNull
  @Min(2)
  private int size;
  private String roleName;
}
