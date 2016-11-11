package com.kybaby.bo.impl;

import com.kybaby.bo.HealthPathBo;
import com.kybaby.dao.HealthPathDao;
import com.kybaby.domain.HealthPath;

/**
 * @ClassName:HealthPathBoImpl
 * @Description:健康计划路径事物管理接口实现
 * @author Hoolee
 * @date 2015年9月30日上午10:08:49
 */
public class HealthPathBoImpl implements HealthPathBo {
	
	HealthPathDao healthPathDao;
	
	public String getPathNameById(long pathId) {
		return healthPathDao.getPathNameById(pathId);
	}
	
	public HealthPath getHealthPathById(long pathId) {
		return healthPathDao.getHealthPathById(pathId);
	}

	public HealthPathDao getHealthPathDao() {
		return healthPathDao;
	}

	public void setHealthPathDao(HealthPathDao healthPathDao) {
		this.healthPathDao = healthPathDao;
	}



}
