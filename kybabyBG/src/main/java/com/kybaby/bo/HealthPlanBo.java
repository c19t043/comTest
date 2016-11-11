package com.kybaby.bo;

import java.util.List;

import com.kybaby.domain.HealthPlan;

public interface HealthPlanBo {

	//2.8.4 健康计划管理
	List getAllHealthPlan();  //找到所有的健康计划
	HealthPlan getHealthPlanByName(String name);  //通过健康计划名字找到该实例
	HealthPlan getHealthPlanById(long id);        //通过健康计划id找到该实例
}
