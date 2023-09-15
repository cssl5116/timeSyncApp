package com.timeSync.www.service;

import com.github.pagehelper.PageInfo;
import com.timeSync.www.dto.RoleSearchForm;
import com.timeSync.www.entity.TbRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleService {
  PageInfo<TbRole> roleList(RoleSearchForm form);
  List<TbRole> selectRole();

  void exists(int id);

  void update(TbRole tbRole);

  void save(TbRole tbRole);
}
