package com.timeSync.www.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class UserSeacherForm {
  @NotNull
  @Min(1)
  private int offset;
  @NotNull
  @Min(2)
  private int size;
  private String name;
  private Integer status;
}
