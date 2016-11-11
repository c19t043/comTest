package com.kybaby.dao;

import java.util.List;

import com.kybaby.domain.HealthPath;
import com.kybaby.domain.HealthPlanRemind;
import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.doctoroperateflow.domain.HealthPlanRemindIssued;

/**
 * @ClassName:HealthPlanRemindDao
 * @Description:健康提醒数据管理接口
 * @author Hoolee
 * @date 2015年9月29日下午3:36:13
 */
public interface HealthPlanRemindDao {
	/**
	 * 
	 * @author HooLee
	 * @Discription:获取今日健康提醒列表
	 * @data: 2015年9月14日下午11:03:45
	 * @param userId 用户的ID
	 * @param strDate 日期
	 * @return 今日健康提醒列表
	 */
	List<HealthPlanRemind> todayHealthPlanRemind(long userId,String strDate);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:获取一个订单编号下面的
	 * @data: 2015年9月29日18:37:49
	 * @param orderNum 订单编号
	 * @param userId 用户的ID
	 * @return 该订单编号下的健康计划的ID集合
	 */
	List<Long> getSomeOrderNumPlanIdList(String orderNum,long userId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:获取某一个健康计划下面的健康计划路劲的Id集合
	 * @data: 2015年9月30日10:35:56
	 * @param orderNum 订单编号
	 * @param userId 用户的ID
	 * @param healthPlanId 健康计划路径的ID
	 * @return 某一个健康计划下面的健康计划路径的ID集合
	 */
	List<Long> getSomeHealthPlanPathIdList(String orderNum,long userId,long healthPlanId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:获取某一个日期的健康提醒实例
	 * @data: 2015年9月30日上午10:59:04
	 * @param orderNum 订单编号
	 * @param userId 用户的ID
	 * @param planId 计划的ID
	 * @param pathId 路径的ID
	 * @return 健康提醒实例  
	 */
	HealthPlanRemind getSomeDateHealthPlanReamin(String orderNum ,long userId,long planId,long pathId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:
	 * @data: 2015年10月13日下午6:28:04
	 * @param userId
	 * @param orderNum
	 * @param pathId
	 * @param planId
	 * @return
	 */
	long getSomePathAmount(long userId,String orderNum,long pathId,long planId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:通过订单编号和用户的ID获取到用户的在该订单中所有健康提醒的列表
	 * @data: 2015年10月14日11:29:09
	 * @param userId 用户的ID
	 * @param orderNum 订单编号
	 * @return 用户的在该订单中所有健康提醒的列表
	 */
	List<HealthPlanRemind> getDateStr(long userId,String orderNum);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:更新健康计划实例
	 * @data: 2015年10月14日下午3:56:42
	 * @param remain 健康计划实例
	 */
	void updateSomeRemain(HealthPlanRemind remain);
	/**
	 * 得到用户做过二保的所有健康指导信息
	 * @param userInfo
	 * @return
	 */
	List<HealthPlanRemindIssued> getHealthPlanRemindIssuedByUser(UserInfo userInfo);
	/**
	 * 根据订单编号得到健康指导
	 * @param orderNum
	 * @return
	 */
	List<HealthPath> getHealthPathListByOrderNum(String orderNum);
}
