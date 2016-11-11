package com.kybaby.bo;

import java.util.List;

import com.kybaby.domain.Activity;
import com.kybaby.domain.Coupon;
import com.kybaby.domain.UserCoupon;

public interface CouponManageBo {

	//2.10.1 优惠卷管理
	List getActivityAndCoupon();//得到所有的活动及优惠卷
	Activity getActivityByName(String name);//通过活动名字找到该实例
	Activity getActivityById(long id);//通过活动id找到该实例
	Coupon getCouponByName(String name);//通过活动名字找到该实例
	Coupon getCouponById(long id);//通过活动id找到该实例
	
	List  getUsefulCoupon(); //得到使用期未过的优惠卷
	List  getAllCoupon(); //得到所有优惠卷  //add by xchao 2015-12-17
    String getNameByCouponId(long id); //通过优惠卷Id得到其名字
	UserCoupon getUserCouponByUserIdAndCouponId(long id, long couponId);
}
