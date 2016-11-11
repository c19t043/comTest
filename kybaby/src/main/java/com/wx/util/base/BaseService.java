package com.wx.util.base;

import com.wx.util.exception.ServiceException;

import java.io.Serializable;
import java.util.List;

public abstract interface BaseService<T, PK extends Serializable>
{
  public abstract PK insert(T paramT)
    throws ServiceException;

  public abstract boolean delete(PK paramPK);

  public abstract T load(T paramT)
    throws ServiceException;

  public abstract boolean update(T paramT);

  public abstract int countAll()
    throws ServiceException;

  public abstract List<T> findAll(T paramT)
    throws ServiceException;

  public abstract List<T> listPage(T paramT)
    throws ServiceException;
  public abstract List<Integer> listCar(T paramT)
  throws ServiceException;

  public abstract T loadByPK(PK paramPK)
    throws ServiceException;

  public abstract boolean insertORupdate(T paramT);
}