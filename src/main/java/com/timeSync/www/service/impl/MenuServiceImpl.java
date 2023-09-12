package com.timeSync.www.service.impl;

import cn.hutool.core.util.ArrayUtil;
import com.timeSync.www.entity.TbMenu;
import com.timeSync.www.mapper.TbMenuMapper;
import com.timeSync.www.service.MenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl implements MenuService {
  @Resource
  private TbMenuMapper tbMenuMapper;

  @Override
  public List<TbMenu> findMenuByTypeId() {
    String[] arr = {"USER:SELECT", "USER:INSERT", "USER:DELETE", "ROLE:SELECT", "ROLE:INSERT"};
    Set<String> collect = Arrays.stream(arr).collect(Collectors.toSet());
    List<TbMenu> one = tbMenuMapper.findMenuByTypeId(1, null);
    for (TbMenu i : one) {
      List<TbMenu> two = tbMenuMapper.findMenuByTypeId(2, i.getId());
      for (TbMenu tbMenu : two) {
        List<TbMenu> three = tbMenuMapper.findMenu(tbMenu.getId());
        three = three.stream()
            .filter(menu -> collect.contains(menu.getPermissionName()))
            .collect(Collectors.toList());
        if (three.size() > 0) tbMenu.setChildren(three);
      }
      if (!"系统总览".equals(i.getName())) {
        two = two.stream()
            .filter(tbMenu -> tbMenu.getChildren() != null
                && tbMenu.getChildren().size() > 0)
            .collect(Collectors.toList());
      }
      i.setChildren(two);
    }
    return one;
  }
}
