package com.wx.service;

import com.wx.persistence.beans.SysMenu;
import com.wx.util.base.BaseService;
import com.wx.util.exception.ServiceException;

import java.util.List;

public abstract interface SysMenuService extends BaseService<SysMenu, Integer>
{
  public abstract List<SysMenu> getTreeByParentId(int paramInt)
    throws ServiceException;

  public abstract String insertOReait(SysMenu paramSysMenu)
    throws ServiceException;

  public abstract boolean delBean(SysMenu paramSysMenu);
}

