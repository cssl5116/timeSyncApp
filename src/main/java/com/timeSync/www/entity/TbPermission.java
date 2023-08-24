package com.timeSync.www.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @TableName tb_permission
 */
@Data
public class TbPermission implements Serializable {

  /**
   * 主键
   */
  @NotNull(message = "[主键]不能为空")
  @ApiModelProperty("主键")
  private Integer id;
  /**
   * 权限
   */
  @NotBlank(message = "[权限]不能为空")
  @ApiModelProperty("权限")
  private String permissionName;
  /**
   * 模块ID
   */
  @NotNull(message = "[模块ID]不能为空")
  @ApiModelProperty("模块ID")
  private Integer moduleId;
  /**
   * 行为ID
   */
  @NotNull(message = "[行为ID]不能为空")
  @ApiModelProperty("行为ID")
  private Integer actionId;
}
