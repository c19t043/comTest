package com.kybaby.bo;

import java.util.List;

import com.kybaby.domain.HealthPath;
import com.kybaby.domain.HealthPlanRemind;
import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.doctoroperateflow.fo.HealthPlanFo;

/**
 * @ClassName:HealthPlanRemindBo
 * @Description:健康提醒事物管理接口
 * @author Hoolee
 * @date 2015年9月14日下午11:02:54
 */
public interface HealthPlanRemindBo {
	
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
	 * @Discription:获取用户在某个订单中的计划路劲执行情况
	 * @data: 2015年9月22日下午2:55:53
	 * @param userId 用户的ID
	 * @param orderNum 订单编号
	 * @param pathId 计划路径ID
	 * @return 执行的次数
	 */
	long getSomeHealthPathAmount(long userId,String orderNum,long pathId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:通过订单编号和用户的ID获取到用户的在当天以及之前所有有计划路劲执行的日期列表
	 * @data: 2015年9月23日下午11:02:32
	 * @param userId 用户的ID
	 * @param orderNum 订单编号
	 * @return 用户的在当天之前所有有计划路劲执行的日期列表
	 */
	List<String> getDateStr(long userId,String orderNum);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:通过用户的ID、健康计划的ID、执行路径的ID和日期获取到某个用户在某个健康计划下的某个健康路径在某一天的执行情况
	 * @data: 2015年9月23日下午11:20:08
	 * @param orderNum 订单编号 
	 * @param userId 用户的ID
	 * @param planId 计划的ID
	 * @param pathId 路径的ID
	 * @param someDate 时间
	 * @return 执行情况
	 */
	String getSomeDateResult(String orderNum,long userId,long planId,long pathId,String someDate);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:获取一个订单编号下面的
	 * @data: 2015年9月29日下午6:20:37
	 * @param orderNum 订单编号
	 * @param userId 用户的ID
	 * @return 该订单编号下的健康计划的ID集合
	 */
	List<Long> getSomeOrderNumPlanIdList(String orderNum,long userId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:获取某个用户在某个订单下，在某一天需要的执行的健康计划ID
	 * @data: 2015年10月24日下午11:25:17
	 * @param orderNum 订单编号
	 * @param userId 用户的ID
	 * @param strDate 当前的日期
	 * @return 在某一天需要执行的健康计划ID
	 */
	List<Long> getTodaySomeOrderNumPlanIdList(String orderNum,long userId,String strDate);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:获取某一个健康计划下面的健康计划路劲的Id集合
	 * @data: 2015年9月30日上午10:32:53
	 * @param orderNum 订单编号
	 * @param userId 用户的ID
	 * @param healthPlanId 健康计划路径的ID
	 * @return 某一个健康计划下面的健康计划路径的ID集合
	 */
	List<Long> getSomeHealthPlanPathIdList(String orderNum,long userId,long healthPlanId);
	
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
	 * @Discription:执行今日的健康计划路径
	 * @data: 2015年10月14日下午3:48:59
	 * @param userId 用户的ID
	 * @param orderNum 订单编号
	 * @param pathId 路径的ID
	 * @param planId 计划的ID
	 */
	void confirmTodayRemain(long userId,String orderNum,long pathId,long planId);
	/**
	 * 健康指导记录列表
	 * @param userInfo
	 */
	List<HealthPlanFo> getHealthPlanFoList(UserInfo userInfo);
	/**
	 * 根据订单编号得到健康指导
	 * @param orderNum
	 * @return
	 */
	List<HealthPath> getHealthPathListByOrderNum(String orderNum);
}
