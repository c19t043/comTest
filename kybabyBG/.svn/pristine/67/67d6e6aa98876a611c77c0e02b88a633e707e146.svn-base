package com.kybaby.newbussiness.operationstrategy.bo.impl;

import java.util.List;

import com.kybaby.domain.Coupon;
import com.kybaby.newbussiness.operationstrategy.bo.OperationStrategyService;
import com.kybaby.newbussiness.operationstrategy.dao.OperationStrategyDao;

public class OperationStrategyServiceImpl implements OperationStrategyService {
	private OperationStrategyDao operationStrategyDao;

	public OperationStrategyDao getOperationStrategyDao() {
		return operationStrategyDao;
	}

	public void setOperationStrategyDao(OperationStrategyDao operationStrategyDao) {
		this.operationStrategyDao = operationStrategyDao;
	}

	@Override
	public List<Coupon> getAllCoupon(Coupon coupon) {
		return this.operationStrategyDao.getAllCoupon(coupon);
	}
}
