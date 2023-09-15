package com.timeSync.www.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.timeSync.www.dto.RoleSearchForm;
import com.timeSync.www.entity.TbRole;
import com.timeSync.www.mapper.TbRoleMapper;
import com.timeSync.www.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author fishx
 * @version 1.0
 * @description: TODO
 * @date 2023/9/13 16:57
 */
@Service
public class RoleServiceImpl implements RoleService {
  @Resource
  TbRoleMapper roleMapper;

  @Override
  public PageInfo<TbRole> roleList(RoleSearchForm form) {
    PageHelper.startPage(form.getOffset(), form.getSize());
    return new PageInfo<>(roleMapper.roleList(form));
  }

  @Override
  public List<TbRole> selectRole() {
    return roleMapper.selectRole();
  }
}
