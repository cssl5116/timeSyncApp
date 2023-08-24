package com.timeSync.www.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @TableName tb_dept
 */
@Data
public class TbDept implements Serializable {
  /**
   * 主键
   */
  @NotNull(message = "[主键]不能为空")
  @ApiModelProperty("主键")
  private Integer id;
  /**
   * 部门名称
   */
  @NotBlank(message = "[部门名称]不能为空")
  @ApiModelProperty("部门名称")
  private String deptName;
}
