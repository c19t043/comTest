package com.kybaby.bo.impl;

import java.util.List;

import com.kybaby.bo.OrderResultsBo;
import com.kybaby.dao.OrderResultsDao;

/**
 * @ClassName:OrderResultsBoImpl
 * @Description:订单结果事务管理接口
 * @author Hoolee
 * @date 2015年10月13日下午4:06:34
 */
public class OrderResultsBoImpl implements OrderResultsBo {

	OrderResultsDao orderResultsDao;
	
	public List<String> getWriteDateListByUserId(long userId) {
		return orderResultsDao.getWriteDateListByUserId(userId);
	}

	public List<Long> getProductIdByUserId(long userId) {
		return null;
	}

	public List<Long> getProductItemIdByUserIdAndProductId(long userId,
			long productId) {
		return null;
	}

	public Long getPlanIdList(long userId, long productId, long itemId) {
		return null;
	}

	public List<String> getOrderNumList(long userId) {
		return null;
	}

	public String getLastOrderNumByUserId(long userId) {
		return orderResultsDao.getLastOrderNumByUserId(userId);
	}

	public List<String> getSomeOrderPlanIdList(long userId, String orderNum) {
		return orderResultsDao.getSomeOrderPlanIdList(userId, orderNum);
	}

	public Long getDoctorIdByOrderNum(String orderNum,long userId) {
		return orderResultsDao.getDoctorIdByOrderNum(orderNum, userId);
	}

	public List<Long> getServiceDoctorIdList(long userId) {
		return null;
	}
	
	public String getWridateByOrderNumUserIdAndProdcutId(String orderNum,long userId, long productId) {
		return orderResultsDao.getWridateByOrderNumUserIdAndProdcutId(orderNum, userId, productId);
	}

	public List<Object[]> getItemIdAndResultValueAndUnit(String orderNum,long userId, long productId) {
		return orderResultsDao.getItemIdAndResultValueAndUnit(orderNum, userId, productId);
	}
	
	public List<String> getHealthPlanId(String orderNum, long userId,long productId) {
		return orderResultsDao.getHealthPlanId(orderNum, userId, productId);
	}

	public List<Object[]> getSomeOrderResult(String orderNum, long userId) {
		return orderResultsDao.getSomeOrderResult(orderNum, userId);
	}

	public OrderResultsDao getOrderResultsDao() {
		return orderResultsDao;
	}

	public void setOrderResultsDao(OrderResultsDao orderResultsDao) {
		this.orderResultsDao = orderResultsDao;
	}
}
