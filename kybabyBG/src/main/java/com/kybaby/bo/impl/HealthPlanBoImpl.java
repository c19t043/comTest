package com.kybaby.bo.impl;

import java.util.List;

import com.kybaby.bo.HealthPlanBo;
import com.kybaby.dao.HealthPlanDao;
import com.kybaby.domain.HealthPlan;

public class HealthPlanBoImpl implements  HealthPlanBo {

	HealthPlanDao healthPlanDao;
	@Override
	public List getAllHealthPlan() {
		// TODO Auto-generated method stub
		return healthPlanDao.getAllHealthPlan();
	}

	@Override
	public HealthPlan getHealthPlanByName(String name) {
		// TODO Auto-generated method stub
		return healthPlanDao.getHealthPlanByName(name);
	}

	@Override
	public HealthPlan getHealthPlanById(long id) {
		// TODO Auto-generated method stub
		return healthPlanDao.getHealthPlanById(id);
	}

	public HealthPlanDao getHealthPlanDao() {
		return healthPlanDao;
	}

	public void setHealthPlanDao(HealthPlanDao healthPlanDao) {
		this.healthPlanDao = healthPlanDao;
	}

//	//2.8.4 健康计划管理
//	List getAllHealthPlan();  //找到所有的健康计划
//	HealthPlan getHealthPlanByName(String name);  //通过健康计划名字找到该实例
//	HealthPlan getHealthPlanById(long id);        //通过健康计划id找到该实例
}
