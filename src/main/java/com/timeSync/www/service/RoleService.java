package com.timeSync.www.service;

import com.github.pagehelper.PageInfo;
import com.timeSync.www.dto.RoleSearchForm;
import com.timeSync.www.entity.TbRole;

import java.util.List;

public interface RoleService {
  PageInfo<TbRole> roleList(RoleSearchForm form);
  List<TbRole> selectRole();
}
