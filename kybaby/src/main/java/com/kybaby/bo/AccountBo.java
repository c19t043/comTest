package com.kybaby.bo;

import java.util.List;

import com.kybaby.domain.Coupon;
import com.kybaby.domain.UserAccount;
import com.kybaby.domain.UserCoupon;
import com.kybaby.domain.UserInfo;
import com.kybaby.domain.UserPoints;

/**
 * @author sujiantang
 *
 */
public interface AccountBo {

	//获取用户实体
	UserInfo getUserInfoByUserId(Long userId);
	
	//获取用户余额
	Double getBalanceByUserId(Long userId);
	
	//获取用户余额明细
	List<UserAccount> getUserAccountByUserId(Long userId);
	
	//获取用户积分
	Long getUserPointByUserId(Long userId);
	
	//获取用户积分明细
	List<UserPoints> getUserPointsByUserId(Long userId);
	
	//获取用户所有优惠券
	List getAllCouponByUserId(Long userId);
	
	//获取可以领取的优惠券ID
	List<Long> getCouponIdsByActivity(String time,String time1);
	
	//获取可以领取的优惠券实例
	Coupon getCouponByCouponId(Long id);
	
	//检查优惠券是否已经领取过
	UserCoupon checkCoupon(Long userId,Long couponId, Long activityId);
	
	//保存用户领取的优惠券
	void saveNewCoupon(UserCoupon newUserCoupon);
}
