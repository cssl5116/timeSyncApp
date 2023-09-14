package com.timeSync.www.mapper;

import com.timeSync.www.entity.TbDept;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.HashMap;

/**
* @author fishx
* @description 针对表【tb_dept】的数据库操作Mapper
* @createDate 2023-08-23 12:03:43
* @Entity com.timeSync.www.entity.TbDept
*/
@Mapper
public interface TbDeptMapper {
    //查询部门成员
    public ArrayList<HashMap> searchDeptMembers(String keyword);
}




