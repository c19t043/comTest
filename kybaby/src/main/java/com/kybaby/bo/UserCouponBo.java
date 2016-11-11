package com.kybaby.bo;

/**
 * 
 * @ClassName:UserCouponBo
 * @Description:用户优惠券事物管理接口
 * @author Hoolee
 * @date 2015年9月16日下午5:54:11
 */
public interface UserCouponBo {
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:更新用户的优惠券使用状态
	 * @data: 2015年9月16日下午6:37:45
	 * @param userId 用户的ID
	 * @param couponId 优惠券的ID
	 */
	void updateUserCoupon(long userId,long couponId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:更新用户的优惠券的使用状态为未使用
	 * @data: 2015年9月21日下午2:04:05
	 * @param userId 用户的ID
	 * @param couponId 优惠券的ID
	 */
	void updateUserSomeCoupon(long userId,long couponId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:判断用户是否领取过某一个活动的某张优惠券
	 * @data: 2015年9月23日下午1:39:51
	 * @param activityId 活动的ID
	 * @param userId 用户的ID
	 * @param couponId 优惠券的ID
	 * @return 是否领取过
	 */
	boolean isHaved(long activityId,long userId,long couponId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:增加一个用户添加某个活动的某张优惠券的记录
	 * @data: 2015年9月23日下午1:44:23
	 * @param activityId 活动的ID
	 * @param userId 用户的ID
	 * @param couponId 优惠券的ID
	 */
	void addNewUserCoupon(long activityId,long userId,long couponId);
}
