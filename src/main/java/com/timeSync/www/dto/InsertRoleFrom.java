package com.timeSync.www.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author lwf
 * &#064;date 2023/9/16 0:51
 */
@ApiModel
@Data
public class InsertRoleFrom {
  private String roleName;

  private Object menuList;

  private Integer id;
}
