package com.timeSync.www.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * 疫情城市列表
 *
 * @TableName tb_city
 */
@Data
public class TbCity implements Serializable {

  /**
   * 主键
   */
  @NotNull(message = "[主键]不能为空")
  @ApiModelProperty("主键")
  private Integer id;
  /**
   * 城市名称
   */
  @NotBlank(message = "[城市名称]不能为空")
  @ApiModelProperty("城市名称")
  private String city;
  /**
   * 拼音简称
   */
  @NotBlank(message = "[拼音简称]不能为空")
  @ApiModelProperty("拼音简称")
  private String code;
}
