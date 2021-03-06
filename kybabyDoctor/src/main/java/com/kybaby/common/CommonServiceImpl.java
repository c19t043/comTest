package com.kybaby.common;

import java.io.Serializable;

public class CommonServiceImpl implements CommonService {
	protected CommonDao commonDao;
	@Override
	public <T> T get(Serializable id, Class<T> clazz) {
		return commonDao.getObjectByID(id, clazz);
	}
	public void setCommonDao(CommonDao commonDao){
		this.commonDao = commonDao;
	}
	@Override
	public <T> void save(T object) {
		this.commonDao.saveObject(object);
	}
	@Override
	public <T> void update(T object) {
		this.commonDao.updateObject(object);
	}
	@Override
	public <T> void delete(Serializable id, Class<T> clazz) {
		commonDao.deleteObject(id, clazz);
	}
}
