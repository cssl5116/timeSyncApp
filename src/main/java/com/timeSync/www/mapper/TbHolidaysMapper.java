package com.timeSync.www.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.Set;

/**
* @author fishx
* @description 针对表【tb_holidays(节假日表)】的数据库操作Mapper
* @createDate 2023-08-23 12:03:43
* @Entity com.timeSync.www.entity.TbHolidays
*/
@Mapper
public interface TbHolidaysMapper {
  boolean saveBatchHoliday(Set<String> holidaysList);
  Integer searchTodayIsHolidays();
}




