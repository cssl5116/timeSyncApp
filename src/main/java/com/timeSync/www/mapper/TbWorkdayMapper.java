package com.timeSync.www.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.Set;

/**
* @author fishx
* @description 针对表【tb_workday】的数据库操作Mapper
* @createDate 2023-08-23 12:03:43
* @Entity com.timeSync.www.entity.TbWorkday
*/
@Mapper
public interface TbWorkdayMapper {
  boolean saveBatchWorkday(Set<String> workdayList);

  Integer searchTodayWorkday();
}




