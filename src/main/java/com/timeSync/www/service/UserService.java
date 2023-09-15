package com.timeSync.www.service;

import com.github.pagehelper.PageInfo;
import com.timeSync.www.dto.UserSeacherForm;
import com.timeSync.www.entity.TbUser;
import com.timeSync.www.utils.R;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public interface UserService {
  int registerUser(String registerCode, String code, String nickname, String photo);

  Set<String> searchUserPermissions(int userId);

  TbUser searchById(int userId);

  Integer login(String code);

  R upload(MultipartFile file, HttpServletRequest request);

  TbUser loginH(String phone, String code);

  R check(String phone);

  ArrayList<HashMap> searchUserGroupByDept(String keyword);

  ArrayList<HashMap> searchMembers(List param);

  PageInfo<TbUser> userList(UserSeacherForm form);
}
