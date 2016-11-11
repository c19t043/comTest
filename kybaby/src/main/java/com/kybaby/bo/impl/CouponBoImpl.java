package com.kybaby.bo.impl;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import com.kybaby.bo.CouponBo;
import com.kybaby.dao.CouponDao;
import com.kybaby.dao.UserCouponDao;
import com.kybaby.domain.Coupon;
import com.kybaby.domain.UserCoupon;

/**
 * @ClassName:CouponBoImpl
 * @Description:优惠券事务管理接口实现
 * @author Hoolee
 * @date 2015年10月7日下午2:42:35
 */
public class CouponBoImpl implements CouponBo {
	
	CouponDao couponDao;
	UserCouponDao userCouponDao;

	public List<Coupon> getUserCoupnListById(long userId) {
		List<Long> couponIdList=userCouponDao.findSomeUserCouponList(userId);
		if(couponIdList!=null){
			String couponIds="(";
			for(int i =0;i<couponIdList.size();i++){
				couponIds=couponIds+couponIdList.get(i)+",";
			}
			int length=couponIds.length();
			couponIds=couponIds.substring(0,length-1);
			couponIds+=")";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date rightNow=new Date(System.currentTimeMillis());
			String dateNow=sdf.format(rightNow);
			List<Coupon> couponList=couponDao.getCoupnListById(couponIds, dateNow);
			return couponList;
		}
		return null;
	}

	public Coupon getCouponInstanceById(long couponId) {
		return null;
	}

	public double getCouponAmountById(long couponId) {
		return couponDao.getCouponAmountById(couponId);
	}

	public List<Coupon> getUserAllCouponList(long userId) {
		return null;
	}

	public List<String> someCouponStatus(long userId) {
		return null;
	}
	
	public boolean isCanBeUsed(long userId, long couponId) {
		//能够使用优惠券是那些用户没有使用，同时也在优惠券的有效期内的
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date rightNow=new Date(System.currentTimeMillis());
		String dateNow=sdf.format(rightNow);
		
		//added by zhong at 2015-10-31
		dateNow=dateNow.substring(0, 10);
		Coupon someCoupon=couponDao.getCouponById(couponId, dateNow);
		UserCoupon userCoupon=userCouponDao.findUserSomeCoupon(userId, couponId);
		if(someCoupon!=null&&userCoupon!=null){
			return true;
		}
		return false;
	}

	public CouponDao getCouponDao() {
		return couponDao;
	}

	public void setCouponDao(CouponDao couponDao) {
		this.couponDao = couponDao;
	}

	public UserCouponDao getUserCouponDao() {
		return userCouponDao;
	}

	public void setUserCouponDao(UserCouponDao userCouponDao) {
		this.userCouponDao = userCouponDao;
	}

}
