package com.timeSync.www.entity;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

/**
 * 行为表
 *
 * @TableName tb_action
 */
public class TbAction implements Serializable {
  @ApiModelProperty("主键")
  private Integer id;
  /**
   * 行为编号
   */
  @ApiModelProperty("行为编号")
  private String actionCode;
  /**
   * 行为名称
   */
  @ApiModelProperty("行为名称")
  private String actionName;
}
