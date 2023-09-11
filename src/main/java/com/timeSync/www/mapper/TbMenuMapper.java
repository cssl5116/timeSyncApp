package com.timeSync.www.mapper;

import com.timeSync.www.entity.TbMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TbMenuMapper {
  List<TbMenu> findMenuByTypeId(int id,Integer pid);
  List<TbMenu> findMenu(Integer pid);
}
