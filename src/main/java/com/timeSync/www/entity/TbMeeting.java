package com.timeSync.www.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 会议表
 *
 * @TableName tb_meeting
 */
@Data
public class TbMeeting implements Serializable {

  /**
   * 主键
   */
  @NotNull(message = "[主键]不能为空")
  @ApiModelProperty("主键")
  private Long id;
  /**
   * UUID
   */
  @ApiModelProperty("UUID")
  private String uuid;
  /**
   * 会议题目
   */
  @NotBlank(message = "[会议题目]不能为空")
  @ApiModelProperty("会议题目")
  private String title;
  /**
   * 创建人ID
   */
  @NotNull(message = "[创建人ID]不能为空")
  @ApiModelProperty("创建人ID")
  private Long creatorId;
  /**
   * 日期
   */
  @NotNull(message = "[日期]不能为空")
  @ApiModelProperty("日期")
  private Date date;
  /**
   * 开会地点
   */
  @ApiModelProperty("开会地点")
  private String place;
  /**
   * 开始时间
   */
  @NotNull(message = "[开始时间]不能为空")
  @ApiModelProperty("开始时间")
  private Date start;
  /**
   * 结束时间
   */
  @NotNull(message = "[结束时间]不能为空")
  @ApiModelProperty("结束时间")
  private Date end;
  /**
   * 会议类型（1在线会议，2线下会议）
   */
  @NotNull(message = "[会议类型（1在线会议，2线下会议）]不能为空")
  @ApiModelProperty("会议类型（1在线会议，2线下会议）")
  private Integer type;
  /**
   * 参与者
   */
  @NotNull(message = "[参与者]不能为空")
  @ApiModelProperty("参与者")
  private Object members;
  /**
   * 会议内容
   */
  @NotBlank(message = "[会议内容]不能为空")
  @ApiModelProperty("会议内容")
  private String desc;
  /**
   * 工作流实例ID
   */
  @ApiModelProperty("工作流实例ID")
  private String instanceId;
  /**
   * 状态（1未开始，2进行中，3已结束）
   */
  @NotNull(message = "[状态（1未开始，2进行中，3已结束）]不能为空")
  @ApiModelProperty("状态（1未开始，2进行中，3已结束）")
  private Integer status;
  /**
   * 创建时间
   */
  @NotNull(message = "[创建时间]不能为空")
  @ApiModelProperty("创建时间")
  private Date createTime;
}
