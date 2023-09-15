package com.timeSync.www.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 角色表
 *
 * @TableName tb_role
 */
@Data
public class TbRole implements Serializable {

  /**
   * 主键
   */
  @NotNull(message = "[主键]不能为空")
  @ApiModelProperty("主键")
  private Integer id;
  /**
   * 角色名称
   */
  @NotBlank(message = "[角色名称]不能为空")
  @ApiModelProperty("角色名称")
  private String roleName;
  /**
   * 权限集合
   */
  @NotNull(message = "[权限集合]不能为空")
  @ApiModelProperty("权限集合")
  private Object permissions;

  @ApiModelProperty("创建时间")
  private Date createAt;

  @ApiModelProperty("更新时间")
  private Date updateAt;
}
