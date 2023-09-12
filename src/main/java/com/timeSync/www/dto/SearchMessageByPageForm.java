package com.timeSync.www.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author fishx
 * @version 1.0
 * @description: TODO
 * @date 2023/9/3 14:25
 */
@Data
@ApiModel
public class SearchMessageByPageForm {
  @NotNull
  @Min(1)
  private Integer page;
  @NotNull
  @Range(min = 1, max = 40)
  private Integer length;
}
