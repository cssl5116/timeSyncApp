package com.timeSync.www.service;

import com.github.pagehelper.PageInfo;
import com.timeSync.www.dto.RoleSearchForm;
import com.timeSync.www.entity.TbRole;
import org.apache.ibatis.annotations.Param;

public interface RoleService {
  PageInfo<TbRole> roleList(RoleSearchForm form);

  void exists(int id);

  void update(TbRole tbRole);

  void save(TbRole tbRole);
}
