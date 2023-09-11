package com.timeSync.www.service;

import com.timeSync.www.entity.TbUser;
import com.timeSync.www.utils.R;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

public interface UserService {
   int registerUser(String registerCode,String code,String nickname,String photo);
   Set<String> searchUserPermissions(int userId);

  TbUser searchById(int userId);
  Integer login(String code);

  R upload(MultipartFile file, HttpServletRequest request);
}