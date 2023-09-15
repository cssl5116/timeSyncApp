package com.timeSync.www.mapper;

import com.timeSync.www.dto.RoleSearchForm;
import com.timeSync.www.entity.TbRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import javax.management.relation.RoleList;
import java.util.List;

/**
 * @author fishx
 * @description 针对表【tb_role(角色表)】的数据库操作Mapper
 * @createDate 2023-08-23 12:03:43
 * @Entity com.timeSync.www.entity.TbRole
 */
@Mapper
public interface TbRoleMapper {
  List<TbRole> roleList(@Param("form") RoleSearchForm form);
}




