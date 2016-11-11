package com.wx.util.base;

import java.io.Serializable;
import java.util.List;

public abstract interface BaseMapper<T, PK extends Serializable>
{
  public abstract PK insert(T paramT);

  public abstract void delete(PK paramPK);

  public abstract T load(T paramT);

  public abstract void update(T paramT);

  public abstract int countAll();

  public abstract List<T> findAll(T paramT);

  public abstract List<T> listPage(T paramT);
  public abstract List<Integer> listCar(T paramT);

  public abstract T loadByPK(PK paramPK);
}
