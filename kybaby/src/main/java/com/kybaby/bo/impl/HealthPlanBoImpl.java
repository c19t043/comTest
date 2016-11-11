package com.kybaby.bo.impl;

import java.util.ArrayList;
import java.util.List;

import com.kybaby.bo.HealthPlanBo;
import com.kybaby.dao.HealthPlanDao;
import com.kybaby.domain.HealthPlan;

/**
 * @ClassName:HealthPlanBoImpl
 * @Description:健康管理计划事物管理接口实现
 * @author Hoolee
 * @date 2015年9月30日上午9:59:25
 */
public class HealthPlanBoImpl implements HealthPlanBo {
	
	HealthPlanDao healthPlanDao;
	
	public String getPlanNameById(long healthId) {
		return null;
	}

	public String getPathIdsById(long healthId) {
		return null;
	}

	public List<Long> getSomePlanPathIdList(long planId) {
		HealthPlan plan=healthPlanDao.getHealthPlanById(planId);
		if(plan!=null){
			String pathIdsList=plan.getHealthPathIds();
			String[] pathIdArr=pathIdsList.split("::");
			List<Long> pathIds=new ArrayList<Long>();
			for(int i =0;i<pathIdArr.length;i++){
				long pathId=Long.valueOf(pathIdArr[i]);
				pathIds.add(pathId);
			}
			return pathIds;
		}
		return null;
	}

	public HealthPlan getHealthPlanById(long planId) {
		return healthPlanDao.getHealthPlanById(planId);
	}

	public HealthPlanDao getHealthPlanDao() {
		return healthPlanDao;
	}

	public void setHealthPlanDao(HealthPlanDao healthPlanDao) {
		this.healthPlanDao = healthPlanDao;
	}

}
