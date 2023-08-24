package com.timeSync.www.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 节假日表
 *
 * @TableName tb_holidays
 */
@Data
public class TbHolidays implements Serializable {

  /**
   * 主键
   */
  @NotNull(message = "[主键]不能为空")
  @ApiModelProperty("主键")
  private Integer id;
  /**
   * 日期
   */
  @NotNull(message = "[日期]不能为空")
  @ApiModelProperty("日期")
  private Date date;
}
