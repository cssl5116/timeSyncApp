package com.timeSync.www.entity;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @TableName sys_config
 */
@Data
public class SysConfig implements Serializable {
  /**
   * 主键
   */
  @ApiModelProperty("主键")
  private Integer id;
  /**
   * 参数名
   */
  @ApiModelProperty("参数名")
  private String paramKey;
  /**
   * 参数值
   */
  @ApiModelProperty("参数值")
  private String paramValue;
  /**
   * 状态
   */
  @ApiModelProperty("状态")
  private Integer status;
  /**
   * 备注
   */
  @ApiModelProperty("备注")
  private String remark;
}
