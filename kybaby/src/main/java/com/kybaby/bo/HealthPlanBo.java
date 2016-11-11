package com.kybaby.bo;

import java.util.List;

import com.kybaby.domain.HealthPlan;

/**
 * @ClassName:HealthPlanBo
 * @Description:健康计划路劲
 * @author Hoolee
 * @date 2015年9月22日下午2:37:08
 */
public interface HealthPlanBo {
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:通过健康计划的ID获取到健康计划的名称
	 * @data: 2015年9月22日下午2:38:04
	 * @param healthId 健康计划的ID
	 * @return 健康计划的名称
	 */
	String getPlanNameById(long healthId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:通过健康计划的ID获取到健康计划路劲的ID组成的字符串
	 * @data: 2015年9月22日下午2:46:12
	 * @param healthId 健康计划的ID
	 * @return 健康计划路劲ID组成字符串
	 */
	String getPathIdsById(long healthId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:通过计划的ID获取到计划中包括的健康路劲的ID列表
	 * @data: 2015年9月23日下午11:14:15
	 * @param planId 计划的ID
	 * @return 健康路劲的ID列表
	 */
	List<Long> getSomePlanPathIdList(long planId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:
	 * @data: 2015年9月30日上午9:36:57
	 * @param planId
	 * @return
	 */
	HealthPlan getHealthPlanById(long planId);
}
