package com.timeSync.www.entity;

import javax.validation.constraints.NotNull;

import java.io.Serializable;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 签到表
 *
 * @TableName tb_checkin
 */
@Data
public class TbCheckin implements Serializable {

  /**
   * 主键
   */
  @ApiModelProperty("主键")
  private Integer id;
  /**
   * 用户ID
   */
  @ApiModelProperty("用户ID")
  private Integer userId;
  /**
   * 签到地址
   */
  @ApiModelProperty("签到地址")
  private String address;
  /**
   * 国家
   */
  @ApiModelProperty("国家")
  private String country;
  /**
   * 省份
   */
  @ApiModelProperty("省份")
  private String province;
  /**
   * 城市
   */
  @ApiModelProperty("城市")
  private String city;
  /**
   * 区划
   */
  @ApiModelProperty("区划")
  private String district;
  /**
   * 考勤结果
   */
  @ApiModelProperty("考勤结果")
  private Integer status;
  /**
   * 风险等级
   */
  @ApiModelProperty("风险等级")
  private Integer risk;
  /**
   * 签到日期
   */
  @NotNull(message = "[签到日期]不能为空")
  @ApiModelProperty("签到日期")
  private Date date;
  /**
   * 签到时间
   */
  @ApiModelProperty("签到时间")
  private Date createTime;
}
