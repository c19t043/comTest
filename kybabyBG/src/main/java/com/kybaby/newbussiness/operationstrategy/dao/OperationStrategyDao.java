package com.kybaby.newbussiness.operationstrategy.dao;

import java.util.List;

import com.kybaby.domain.Coupon;

public interface OperationStrategyDao {
	/**
	 * 得到优惠券列表
	 * @param coupon
	 * @return
	 */
	List<Coupon> getAllCoupon(Coupon coupon);
}
