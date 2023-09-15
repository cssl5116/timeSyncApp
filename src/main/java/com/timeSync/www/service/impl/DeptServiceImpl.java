package com.timeSync.www.service.impl;

import com.timeSync.www.entity.TbDept;
import com.timeSync.www.mapper.TbDeptMapper;
import com.timeSync.www.service.DeptService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
  @Resource
  private TbDeptMapper deptMapper;

  @Override
  public List<TbDept> selectDept() {
    return deptMapper.selectDept();
  }
}
