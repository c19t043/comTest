package com.kybaby.dao;

import java.util.List;

/**
 * @ClassName:OrderResultsDao
 * @Description:订单结果数据管理接口
 * @author Hoolee
 * @date 2015年10月13日下午4:09:02
 */
public interface OrderResultsDao {

	/**
	 * 
	 * @author HooLee
	 * @Discription:根据用户的ID获取到医生的记录结果日期列表
	 * @data: 2015年10月13日16:22:53
	 * @param userId 用户的ID
	 * @return 医生的记录结果日期列表
	 */
	List<String> getWriteDateListByUserId(long userId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:获取医生最新记录结果日期
	 * @data: 2015年10月13日下午5:06:23
	 * @param orderNum 订单编号
	 * @param userId 用户的ID
	 * @param productId 商品的ID
	 * @return 医生最新记录结果日期
	 */
	String getWridateByOrderNumUserIdAndProdcutId(String orderNum,long userId,long productId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:获取一个用户在某订单中的产品项目ID结果值和单位
	 * @data: 2015年10月13日下午5:43:16
	 * @param orderNum 订单编号
	 * @param userId 用户的ID
	 * @param productId 商品的ID
	 * @return 某订单中的产品项目ID结果值和单位
	 */
	List<Object[]> getItemIdAndResultValueAndUnit(String orderNum,long userId,long productId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:获取某一个用户的某订单下的健康计划路劲ID列表
	 * @data: 2015年10月13日下午6:07:42
	 * @param orderNum 订单编号
	 * @param userId 用户的ID
	 * @param productId 商品的ID
	 * @return 健康计划路劲的ID列表
	 */
	List<String> getHealthPlanId(String orderNum,long userId,long productId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:通过用户的ID获取到用户的最近一次儿保的订单编号
	 * @data: 2015年9月23日下午10:57:04
	 * @param userId 用户的ID
	 * @return 最近一次儿保的订单编号
	 */
	String getLastOrderNumByUserId(long userId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:通过订单编号和用户的ID，获取到用户的健康计划ID列表
	 * @data: 2015年10月14日11:04:20
	 * @param userId 用户的ID
	 * @param orderNum 订单编号
	 * @return 用户的健康计划ID列表
	 */
	List<String> getSomeOrderPlanIdList(long userId,String orderNum);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:通过订单的编号获取到执行订单的医生ID
	 * @data: 2015年10月14日13:55:53
	 * @param orderNum 订单编号
	 * @param userId 用户的ID
	 * @return 医生的ID
	 */
	Long getDoctorIdByOrderNum(String orderNum,long userId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:查询某个用户的某个订单的
	 * @data: 2015年12月4日下午3:47:56
	 * @param orderNum
	 * @param userId
	 * @return
	 */
	List<Object[]> getSomeOrderResult(String orderNum,long userId);
}
