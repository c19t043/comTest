package com.kybaby.bo.impl;

import com.kybaby.bo.UserCouponBo;
import com.kybaby.dao.UserCouponDao;
import com.kybaby.domain.UserCoupon;

/**
 * @ClassName:UserCouponBoImpl
 * @Description:用户优惠券事物管理实现
 * @author Hoolee
 * @date 2015年9月28日上午11:52:01
 */
public class UserCouponBoImpl implements UserCouponBo {
	
	UserCouponDao userCouponDao;
	
	public void addNewUserCoupon(long activityId, long userId, long couponId) {
		UserCoupon userCoupon=new UserCoupon();
		userCoupon.setActivityId(activityId);
		userCoupon.setCouponId(couponId);
		userCoupon.setUserId(userId);
		userCoupon.setCouponStatus("N");
		userCouponDao.addNewUserCoupon(userCoupon);
	}

	public void updateUserCoupon(long userId, long couponId) {
		UserCoupon userCoupon=userCouponDao.findUserSomeCoupon(userId, couponId);
		if(userCoupon!=null){
			userCoupon.setCouponStatus("Y");
			userCouponDao.updateUserCoupon(userCoupon);
		}
		
	}

	public void updateUserSomeCoupon(long userId, long couponId) {
		
	}

	public boolean isHaved(long activityId, long userId, long couponId) {
		return false;
	}

	public UserCouponDao getUserCouponDao() {
		return userCouponDao;
	}

	public void setUserCouponDao(UserCouponDao userCouponDao) {
		this.userCouponDao = userCouponDao;
	}

}
