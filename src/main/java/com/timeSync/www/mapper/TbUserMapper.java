package com.timeSync.www.mapper;

import com.timeSync.www.dto.UserSeacherForm;
import com.timeSync.www.entity.TbUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
  public TbUser havaCode(String code);

  public int insert(HashMap hashMap);
  public int updateQ(String openId,String nickname,String photo,String code);
  public Integer searchIdByOpenId(String openId);

  public Set<String> searchUserPermissions(int userId);

  TbUser searchById(int userId);

  int updateImg(int userId, String url);

  TbUser loginH(String phone);

  boolean check(String phone);

  //根据部门查询员工分组
  public ArrayList<HashMap> searchUserGroupByDept(String keyword);

  //查询会议成员信息
  public ArrayList<HashMap> searchMembers(List param);

  //搜索用户信息
  public HashMap searchUserInfo(int userId);

  //搜索部门经理id
  public int searchDeptManagerId(int id);


  public int searchGmId();

  List<TbUser> selectUser(@Param("form") UserSeacherForm form);

  int insertUser(TbUser user);



}




