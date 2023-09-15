package com.timeSync.www.service.impl;

import com.timeSync.www.mapper.TbDeptMapper;
import com.timeSync.www.service.DeptService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
  @Resource
  private TbDeptMapper deptMapper;

  @Override
  public List<HashMap> selectDept() {
    List<HashMap> tbDepts = new ArrayList<>();
    deptMapper.selectDept().forEach(i -> {
      HashMap<Object, Object> map = new HashMap<>();
      map.put("label",i.getDeptName());
      map.put("value",i.getId());
      tbDepts.add(map);
    });
    return tbDepts;
  }
}
