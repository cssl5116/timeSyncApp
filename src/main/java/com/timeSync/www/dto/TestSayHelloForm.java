package com.timeSync.www.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author fishx
 * @version 1.0
 * @description: 测试数据传输对象校验
 * @date 2023/8/23 16:25
 */
@ApiModel
@Data
public class TestSayHelloForm {
  @NotBlank
  @Pattern(regexp = "^[\\u4e00-\\u9fa5]{2,15}$")
  @ApiModelProperty("姓名")
  private String name;

  @ApiModelProperty("其他补充信息")
  private String other;
}
