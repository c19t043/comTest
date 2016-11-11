package com.kybaby.bo;

import com.kybaby.domain.HealthPath;

/**
 * @ClassName:HealthPathBo
 * @Description:健康计划路径的事物管理
 * @author Hoolee
 * @date 2015年9月22日下午2:49:41
 */
public interface HealthPathBo {
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:通过计划路劲的ID获取到计划路径的名称
	 * @data: 2015年9月22日下午2:50:55
	 * @param pathId 计划路劲的ID
	 * @return 计划路劲的名称
	 */
	String getPathNameById(long pathId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:通过计划路劲的ID获取到计划路径的实例
	 * @data: 2015年9月30日上午10:46:49
	 * @param pathId 计划路径的ID
	 * @return 计划路径的实例
	 */
	HealthPath getHealthPathById(long pathId);
}
