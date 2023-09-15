package com.timeSync.www.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * 用户表
 *
 * @TableName tb_user
 */
@Data
public class TbUser implements Serializable {

  /**
   * 主键
   */

  private Integer id;
  /**
   * 长期授权字符串
   */
  @ApiModelProperty("长期授权字符串")
  private String openId;
  /**
   * 昵称
   */
  @ApiModelProperty("昵称")
  private String nickname;
  /**
   * 头像网址
   */
  @ApiModelProperty("头像网址")
  private String photo;
  /**
   * 姓名
   */
  @ApiModelProperty("姓名")
  private String name;
  /**
   * 性别
   */
  @ApiModelProperty("性别")
  private String sex;
  /**
   * 手机号码
   */
  @ApiModelProperty("手机号码")
  private String tel;
  /**
   * 邮箱
   */
  @ApiModelProperty("邮箱")
  private String email;
  /**
   * 入职日期
   */
  @ApiModelProperty("入职日期")
  private Date hiredate;
  /**
   * 角色
   */
  @ApiModelProperty("角色")
  private Object role;
  /**
   * 是否是超级管理员
   */
  @ApiModelProperty("是否是超级管理员")
  private Integer root;
  /**
   * 部门编号
   */
  @ApiModelProperty("部门编号")
  private Integer deptId;
  /**
   * 状态
   */
  @ApiModelProperty("状态")
  private Integer status;
  /**
   * 创建时间
   */
  @ApiModelProperty("创建时间")
  private Date createTime;
  private String code;
}
