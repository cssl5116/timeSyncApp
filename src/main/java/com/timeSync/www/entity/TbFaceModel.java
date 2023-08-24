package com.timeSync.www.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @TableName tb_face_model
 */
@Data
public class TbFaceModel implements Serializable {
  /**
   * 主键值
   */
  @NotNull(message = "[主键值]不能为空")
  @ApiModelProperty("主键值")
  private Integer id;
  /**
   * 用户ID
   */
  @NotNull(message = "[用户ID]不能为空")
  @ApiModelProperty("用户ID")
  private Integer userId;
  /**
   * 用户人脸模型
   */
  @NotBlank(message = "[用户人脸模型]不能为空")
  @ApiModelProperty("用户人脸模型")
  private String faceModel;
}
