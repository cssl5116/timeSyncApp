package com.timeSync.www.service.impl;

import com.timeSync.www.entity.TbMenu;
import com.timeSync.www.mapper.TbMenuMapper;
import com.timeSync.www.service.TbMenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TbMenuServiceImpl implements TbMenuService {
  @Resource
  private TbMenuMapper tbMenuMapper;

  @Override
  public List<TbMenu> findMenuByTypeId() {
    List<TbMenu> one = tbMenuMapper.findMenuByTypeId(1, null);
    for (TbMenu i : one) {
      List<TbMenu> two = tbMenuMapper.findMenuByTypeId(2, i.getId());
      i.setChildren(two);
      for (TbMenu tbMenu : two) {
        tbMenu.setChildren(tbMenuMapper.findMenu(tbMenu.getId()));
      }
    }
    return one;
  }
}
