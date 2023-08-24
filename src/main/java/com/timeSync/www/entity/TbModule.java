package com.timeSync.www.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 模块资源表
 *
 * @TableName tb_module
 */
@Data
public class TbModule implements Serializable {

  /**
   * 主键
   */
  @NotNull(message = "[主键]不能为空")
  @ApiModelProperty("主键")
  private Integer id;
  /**
   * 模块编号
   */
  @NotBlank(message = "[模块编号]不能为空")
  @ApiModelProperty("模块编号")
  private String moduleCode;
  /**
   * 模块名称
   */
  @NotBlank(message = "[模块名称]不能为空")
  @ApiModelProperty("模块名称")
  private String moduleName;
}
