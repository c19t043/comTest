package com.kybaby.bo.impl;

import java.util.List;

import com.kybaby.bo.HealthPathBo;
import com.kybaby.dao.HealthPathDao;
import com.kybaby.domain.HealthPath;

public class HealthPathBoImpl  implements  HealthPathBo{
	HealthPathDao healthPathDao;
	
	public List<HealthPath> getAllHealthPath() {
		// TODO Auto-generated method stub
		return healthPathDao.getAllHealthPath();
	}

	@Override
	public HealthPath getHealthPathByName(String name) {
		// TODO Auto-generated method stub
		return healthPathDao.getHealthPathByName(name);
	}

	@Override
	public HealthPath  getHealthPathById(long id) {
		// TODO Auto-generated method stub
		return healthPathDao.getHealthPathById(id);
	}

	@Override
	public List getIdAndNameOfHealthPathByStatus(String status) {
		// TODO Auto-generated method stub
		return healthPathDao.getIdAndNameOfHealthPathByStatus(status);
	}

	public HealthPathDao getHealthPathDao() {
		return healthPathDao;
	}

	public void setHealthPathDao(HealthPathDao healthPathDao) {
		this.healthPathDao = healthPathDao;
	}
	
//    // 2.8.5 健康路径管理
//	List<HealthPath> getAllHealthPath();//得到所有的健康路径
//	List<HealthPath> getHealthPathByName(String name);//通过健康路径名字得到该实例
//	List<HealthPath> getHealthPathById(long id);      //通过健康路径id得到该实例
//	
//	//2.8.4 健康计划管理
//	List getIdAndNameOfHealthPathByStatus(String status); //通过status找到该健康路径的id,与healthPathName
}
