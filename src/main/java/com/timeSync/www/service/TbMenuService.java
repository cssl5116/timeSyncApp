package com.timeSync.www.service;

import com.timeSync.www.entity.TbMenu;

import java.util.List;

public interface TbMenuService {
  List<TbMenu> findMenuByTypeId();
}
