package com.timeSync.www.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class TbMenu implements Serializable {
  private Integer id;

  private String icon;

  private String name;

  private String url;
  /**
   * 权限表外键
   */
  private Integer permissionId;

  private Integer type;

  private Integer parent;

  private Date createAt;

  private Date updateAt;

  private String permissionName;

  private List<TbMenu> children;
}
