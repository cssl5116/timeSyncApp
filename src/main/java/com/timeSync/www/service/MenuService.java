package com.timeSync.www.service;

import com.timeSync.www.entity.TbMenu;

import java.util.List;

public interface MenuService {
  List<TbMenu> findMenuByTypeId(int userId);

  List<TbMenu> menus();
}
