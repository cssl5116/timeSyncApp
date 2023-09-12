package com.timeSync.www.mapper;

import com.timeSync.www.entity.SysConfig;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author fishx
* @Description 针对表【sys_config】的数据库操作Mapper
* @CreateDate 2023-08-23 12:03:43
* @Entity com.timeSync.www.entity.SysConfig
*/
@Mapper
public interface SysConfigMapper {
  List<SysConfig> selectAllParam();
}




