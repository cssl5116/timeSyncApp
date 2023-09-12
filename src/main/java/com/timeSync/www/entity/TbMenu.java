package com.timeSync.www.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @TableName tb_menu
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TbMenu implements Serializable {
  private Integer id;
  private String icon;
  private String name;
  private String url;
  private Integer permissionid;
  private Integer type;
  private Integer parent;
  private Date createat;
  private Date updateat;
  private String permissionName;
  private List<TbMenu> children;
}
