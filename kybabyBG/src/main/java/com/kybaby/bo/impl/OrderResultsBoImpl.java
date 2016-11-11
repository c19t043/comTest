package com.kybaby.bo.impl;

import java.util.List;

import com.kybaby.bo.OrderResultsBo;
import com.kybaby.dao.OrderResultsDao;

public class OrderResultsBoImpl implements  OrderResultsBo{

	OrderResultsDao orderResultsDao;
	@Override
	public List getOrderAndPathResult(long userId) {
		
		return orderResultsDao.getOrderAndPathResult(userId);
	}
	public OrderResultsDao getOrderResultsDao() {
		return orderResultsDao;
	}
	public void setOrderResultsDao(OrderResultsDao orderResultsDao) {
		this.orderResultsDao = orderResultsDao;
	}
	@Override
	public List getUserOrderNumListString(long userId) {
		// TODO Auto-generated method stub
		return orderResultsDao.getUserOrderNumListString(userId);
	}
	

//	    //2.5.2  健康档案管理
//		//得到形如产品名字，项目名字，项目结果，项目结果值，项目计划名字，健康计划路径名字，执行情况（Y，N，Y）记录，其中项目名字与计划重复
//		List getOrderAndPathResult(long userId);//通过用户Id得到一次订单的所有结果，细分到健康路径下
}
