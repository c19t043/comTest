package com.kybaby.bo.impl;

import java.util.List;

import com.kybaby.bo.CouponManageBo;
import com.kybaby.dao.CouponManageDao;
import com.kybaby.domain.Activity;
import com.kybaby.domain.Coupon;
import com.kybaby.domain.UserCoupon;

public class CouponManageBoImpl implements  CouponManageBo  {

	CouponManageDao couponManageDao;
	@Override
	public List getActivityAndCoupon() {
		// TODO Auto-generated method stub
		return couponManageDao.getActivityAndCoupon();
	}

	@Override
	public Activity getActivityByName(String name) {
		// TODO Auto-generated method stub
		return couponManageDao.getActivityByName(name);
	}

	@Override
	public Activity getActivityById(long id) {
		// TODO Auto-generated method stub
		return couponManageDao.getActivityById(id);
	}

	@Override
	public Coupon getCouponByName(String name) {
		// TODO Auto-generated method stub
		return couponManageDao.getCouponByName(name);
	}

	@Override
	public Coupon getCouponById(long id) {
		// TODO Auto-generated method stub
		return couponManageDao.getCouponById(id);
	}

	@Override
	public List getUsefulCoupon() {
		// TODO Auto-generated method stub
		return couponManageDao.getUsefulCoupon();
	}
	
	@Override
	public List getAllCoupon() {
		// TODO Auto-generated method stub
		return couponManageDao.getAllCoupon();
	}

	@Override
	public String getNameByCouponId(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public CouponManageDao getCouponManageDao() {
		return couponManageDao;
	}

	public void setCouponManageDao(CouponManageDao couponManageDao) {
		this.couponManageDao = couponManageDao;
	}

	@Override
	public UserCoupon getUserCouponByUserIdAndCouponId(long id, long couponId) {
		// TODO Auto-generated method stub
		return couponManageDao.getUserCouponByUserIdAndCouponId(id, couponId);
	}

//	//2.10.1 优惠卷管理
//	List getActivityAndCoupon();//得到所有的活动及优惠卷
//	Activity getActivityByName(String name);//通过活动名字找到该实例
//	Activity getActivityById(long id);//通过活动id找到该实例
//	Coupon getCouponByName(String name);//通过活动名字找到该实例
//	Coupon getCouponById(long id);//通过活动id找到该实例
//	
//	List  getUsefulCoupon(); //得到使用期未过的优惠卷
//    String getNameByCouponId(long id); //通过优惠卷Id得到其名字
	
}
