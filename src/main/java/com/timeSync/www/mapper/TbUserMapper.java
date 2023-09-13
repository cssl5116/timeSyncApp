package com.timeSync.www.mapper;

import com.timeSync.www.entity.TbUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.Set;

/**
 * @author fishx
 * @description 针对表【tb_user(用户表)】的数据库操作Mapper
 * @createDate 2023-08-23 12:03:43
 * @Entity com.timeSync.www.entity.TbUser
 */
@Mapper
public interface TbUserMapper {
  public boolean havaRootUser();

  public int insert(HashMap hashMap);

  public Integer searchIdByOpenId(String openId);

  public Set<String> searchUserPermissions(int userId);

  TbUser searchById(int userId);

  int updateImg(int userId, String url);

  TbUser loginH(String phone);

  boolean check(String phone);
}




