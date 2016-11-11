package com.kybaby.dao;

import java.util.List;

import com.kybaby.domain.UserCoupon;

/**
 * @ClassName:UserCouponDao
 * @Description:用户优惠券数据管理接口
 * @author Hoolee
 * @date 2015年9月28日上午11:53:39
 */
public interface UserCouponDao {
	/**
	 * 
	 * @author HooLee
	 * @Discription:增加一个用户添加某个活动的某张优惠券的记录
	 * @data: 2015年9月23日下午1:44:23
	 */
	void addNewUserCoupon(UserCoupon userCoupon);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:获取某个用户没有使用的优惠券的ID列表
	 * @data: 2015年10月7日下午3:23:30
	 * @param userId 用户的ID
	 * @return 某个用户的没有使用的优惠券的ID列表
	 */
	List<Long> findSomeUserCouponList(long userId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:查询用户的某ID优惠券
	 * @data: 2015年10月8日下午9:41:22
	 * @param userId 用户的ID
	 * @param couponId 优惠券的ID
	 * @return 用户优惠券实例
	 */
	UserCoupon findUserSomeCoupon(long userId,long couponId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:更新用户优惠券
	 * @data: 2015年10月8日下午9:57:12
	 * @param userCoupon 用户优惠券实例
	 */
	void updateUserCoupon(UserCoupon userCoupon);
}
